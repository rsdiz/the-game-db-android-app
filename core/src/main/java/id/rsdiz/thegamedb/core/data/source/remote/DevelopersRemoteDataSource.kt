package id.rsdiz.thegamedb.core.data.source.remote

import id.rsdiz.thegamedb.core.data.source.remote.mapper.DevelopersRemoteMapper
import id.rsdiz.thegamedb.core.data.source.remote.network.ApiResponse
import id.rsdiz.thegamedb.core.data.source.remote.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DevelopersRemoteDataSource @Inject constructor(
    private val apiService: ApiService,
    val mapper: DevelopersRemoteMapper
) {
    suspend fun getListDevelopers() =
        flow {
            try {
                val response = apiService.getListDevelopers()
                if (response.results.isNotEmpty()) emit(ApiResponse.Success(response.results))
                else emit(ApiResponse.Empty)
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.localizedMessage ?: e.toString()))
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getDetailDevelopers(id: Int) =
        flow {
            try {
                val response = apiService.getDevelopers(id)
                if (response.description?.isNotEmpty() == true) emit(ApiResponse.Success(response))
                else emit(ApiResponse.Empty)
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.localizedMessage ?: e.toString()))
            }
        }.flowOn(Dispatchers.IO)
}
