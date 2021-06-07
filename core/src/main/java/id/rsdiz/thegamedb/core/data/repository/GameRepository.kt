package id.rsdiz.thegamedb.core.data.repository

import id.rsdiz.thegamedb.core.data.NetworkBoundResource
import id.rsdiz.thegamedb.core.data.Resource
import id.rsdiz.thegamedb.core.data.source.local.GameLocalDataSource
import id.rsdiz.thegamedb.core.data.source.remote.GameRemoteDataSource
import id.rsdiz.thegamedb.core.data.source.remote.network.ApiResponse
import id.rsdiz.thegamedb.core.data.source.remote.response.games.GameResponse
import id.rsdiz.thegamedb.core.domain.model.Game
import id.rsdiz.thegamedb.core.domain.repository.IGameRepository
import id.rsdiz.thegamedb.core.utils.AppExecutors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Suppress("UNCHECKED_CAST")
@Singleton
class GameRepository @Inject constructor(
    private val remoteDataSource: GameRemoteDataSource,
    private val localDataSource: GameLocalDataSource,
    private val appExecutors: AppExecutors
) : IGameRepository {
    override fun getGames(): Flow<Resource<List<Game>>> =
        object : NetworkBoundResource<List<Game>, List<GameResponse>>() {
            override fun loadFromDB(): Flow<List<Game>?> = localDataSource.getAllGames().map {
                localDataSource.mapper.mapFromEntities(it)
            }

            override fun shouldFetch(data: List<Game>?): Boolean = data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<GameResponse>>> =
                remoteDataSource.getListGames()

            override suspend fun saveCallResult(data: List<GameResponse>) =
                remoteDataSource.mapper.mapRemoteToEntities(data).let {
                    localDataSource.insertAll(it)
                }
        }.asFlow() as Flow<Resource<List<Game>>>

    override fun getFavoriteGames(): Flow<List<Game>> = localDataSource.getFavoriteGames().map {
        localDataSource.mapper.mapFromEntities(it)
    }

    override fun getDetailGame(id: Int): Flow<Resource<Game>> =
        object : NetworkBoundResource<Game, GameResponse>() {
            override fun loadFromDB(): Flow<Game>? = localDataSource.getGameById(id)?.map {
                localDataSource.mapper.mapFromEntity(it)
            }

            override fun shouldFetch(data: Game?): Boolean = data?.description.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<GameResponse>> =
                remoteDataSource.getDetailGame(id)

            override suspend fun saveCallResult(data: GameResponse) =
                remoteDataSource.mapper.mapRemoteToEntity(data).let {
                    localDataSource.insert(it)
                }
        }.asFlow() as Flow<Resource<Game>>

    override fun setFavoriteGame(game: Game) =
        localDataSource.mapper.mapToEntity(game).let { entity ->
            entity.isFavorite = !entity.isFavorite
            appExecutors.diskIO().execute { localDataSource.update(entity) }
        }

    override suspend fun getGamesByDeveloper(developer: String): Resource<List<Game>> =
        when (val response = remoteDataSource.getGamesByDeveloper(developer).first()) {
            is ApiResponse.Success -> {
                val entities = remoteDataSource.mapper.mapRemoteToEntities(response.data)
                val domain = localDataSource.mapper.mapFromEntities(entities)
                Resource.Success(domain)
            }
            is ApiResponse.Empty -> {
                Resource.Error(response.toString(), null)
            }
            else -> Resource.Error((response as ApiResponse.Error).errorMessage, null)
        }

    override suspend fun searchGame(query: String): Resource<List<Game>> =
        when (val response = remoteDataSource.searchGame(query).first()) {
            is ApiResponse.Success -> {
                val entities = remoteDataSource.mapper.mapRemoteToEntities(response.data)
                val domain = localDataSource.mapper.mapFromEntities(entities)
                Resource.Success(domain)
            }
            is ApiResponse.Empty -> {
                Resource.Error(response.toString(), null)
            }
            else -> Resource.Error((response as ApiResponse.Error).errorMessage, null)
        }

    override suspend fun insertGame(game: Game) =
        localDataSource.mapper.mapToEntity(game).let { entity ->
            localDataSource.insert(entity)
        }
}
