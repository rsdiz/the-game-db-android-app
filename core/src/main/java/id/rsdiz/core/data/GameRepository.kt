package id.rsdiz.core.data

import id.rsdiz.core.data.source.local.LocalDataSource
import id.rsdiz.core.data.source.remote.RemoteDataSource
import id.rsdiz.core.data.source.remote.network.ApiResponse
import id.rsdiz.core.data.source.remote.response.GameResponse
import id.rsdiz.core.domain.model.Game
import id.rsdiz.core.domain.repository.IGameRepository
import id.rsdiz.core.utils.AppExecutors
import id.rsdiz.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Suppress("UNCHECKED_CAST")
@Singleton
class GameRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IGameRepository {
    override fun getGames(): Flow<Resource<List<Game>>> =
        object : NetworkBoundResource<List<Game>, List<GameResponse>>() {
            override fun loadFromDB(): Flow<List<Game>?> = localDataSource.getAllGames().map {
                DataMapper.mapEntitiesToDomain(it)
            }

            override fun shouldFetch(data: List<Game>?): Boolean = data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<GameResponse>>> =
                remoteDataSource.getListGames()

            override suspend fun saveCallResult(data: List<GameResponse>) =
                DataMapper.mapResponsesToEntities(data).let {
                    localDataSource.insertAllGame(it)
                }
        }.asFlow() as Flow<Resource<List<Game>>>

    override fun getFavoriteGames(): Flow<List<Game>> = localDataSource.getFavoriteGames().map {
        DataMapper.mapEntitiesToDomain(it)
    }

    override fun getDetailGame(id: Int): Flow<Resource<Game>> =
        object : NetworkBoundResource<Game, GameResponse>() {
            override fun loadFromDB(): Flow<Game>? = localDataSource.getGameById(id)?.map {
                DataMapper.mapEntityToDomain(it)
            }

            override fun shouldFetch(data: Game?): Boolean = data?.description.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<GameResponse>> =
                remoteDataSource.getDetailGame(id)

            override suspend fun saveCallResult(data: GameResponse) =
                DataMapper.mapResponseToEntity(data).let {
                    localDataSource.insertGame(it)
                }
        }.asFlow() as Flow<Resource<Game>>

    override fun setFavoriteGame(game: Game) =
        DataMapper.mapDomainToEntity(game).let { entity ->
            entity.isFavorite = !entity.isFavorite
            appExecutors.diskIO().execute { localDataSource.updateGame(entity) }
        }

    override fun getAllDevelopers(): Flow<List<String>> {
        TODO("Not yet implemented")
    }

    override fun getGamesByDeveloper(developer: String): Flow<Resource<List<Game>>> =
        object : NetworkBoundResource<List<Game>, List<GameResponse>>() {
            override fun loadFromDB(): Flow<List<Game>?> =
                localDataSource.getListGameByDeveloper(developer).map {
                    DataMapper.mapEntitiesToDomain(it)
                }

            override fun shouldFetch(data: List<Game>?): Boolean = data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<GameResponse>>> =
                remoteDataSource.getGamesByDeveloper(developer)

            override suspend fun saveCallResult(data: List<GameResponse>) =
                DataMapper.mapResponsesToEntities(data).let {
                    localDataSource.insertAllGame(it)
                }
        }.asFlow() as Flow<Resource<List<Game>>>

    override suspend fun searchGame(query: String): Resource<List<Game>> =
        when (val response = remoteDataSource.searchGame(query).first()) {
            is ApiResponse.Success -> {
                val entities = DataMapper.mapResponsesToEntities(response.data)
                val domain = DataMapper.mapEntitiesToDomain(entities)
                Resource.Success(domain)
            }
            is ApiResponse.Empty -> {
                Resource.Error(response.toString(), null)
            }
            is ApiResponse.Error -> {
                Resource.Error(response.errorMessage, null)
            }
        }

    override suspend fun insertGame(game: Game) =
        DataMapper.mapDomainToEntity(game).let { entity ->
            appExecutors.diskIO().execute { localDataSource.updateGame(entity) }
        }
}
