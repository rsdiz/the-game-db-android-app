package id.rsdiz.thegamedb.core.data.repository

import id.rsdiz.thegamedb.core.data.NetworkBoundResource
import id.rsdiz.thegamedb.core.data.Resource
import id.rsdiz.thegamedb.core.data.source.local.DevelopersLocalDataSource
import id.rsdiz.thegamedb.core.data.source.remote.DevelopersRemoteDataSource
import id.rsdiz.thegamedb.core.data.source.remote.network.ApiResponse
import id.rsdiz.thegamedb.core.data.source.remote.response.developer.DeveloperResponse
import id.rsdiz.thegamedb.core.domain.model.Developers
import id.rsdiz.thegamedb.core.domain.repository.IDevelopersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Suppress("UNCHECKED_CAST")
@Singleton
class DevelopersRepository @Inject constructor(
    private val remoteDataSource: DevelopersRemoteDataSource,
    private val localDataSource: DevelopersLocalDataSource
) : IDevelopersRepository {
    override fun getDevelopers(): Flow<Resource<List<Developers>>> =
        object : NetworkBoundResource<List<Developers>, List<DeveloperResponse>>() {
            override fun loadFromDB(): Flow<List<Developers>?> =
                localDataSource.getAllDevelopers().map {
                    localDataSource.mapper.mapFromEntities(it)
                }

            override fun shouldFetch(data: List<Developers>?): Boolean = data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<DeveloperResponse>>> =
                remoteDataSource.getListDevelopers()

            override suspend fun saveCallResult(data: List<DeveloperResponse>) =
                remoteDataSource.mapper.mapRemoteToEntities(data).let {
                    localDataSource.insertAll(it)
                }
        }.asFlow() as Flow<Resource<List<Developers>>>

    override fun getDetailDevelopers(id: Int): Flow<Resource<Developers>> =
        object : NetworkBoundResource<Developers, DeveloperResponse>() {
            override fun loadFromDB(): Flow<Developers>? =
                localDataSource.getDevelopersById(id)?.map {
                    localDataSource.mapper.mapFromEntity(it)
                }

            override fun shouldFetch(data: Developers?): Boolean = data?.description.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<DeveloperResponse>> =
                remoteDataSource.getDetailDevelopers(id)

            override suspend fun saveCallResult(data: DeveloperResponse) =
                remoteDataSource.mapper.mapRemoteToEntity(data).let {
                    localDataSource.insert(it)
                }
        }.asFlow() as Flow<Resource<Developers>>
}
