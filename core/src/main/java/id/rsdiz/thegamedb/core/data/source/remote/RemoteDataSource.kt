package id.rsdiz.thegamedb.core.data.source.remote

import id.rsdiz.thegamedb.core.data.source.remote.network.ApiResponse
import id.rsdiz.thegamedb.core.data.source.remote.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getListGames() =
        flow {
            try {
                val response = apiService.getListGames()
                if (response.results.isNotEmpty()) emit(ApiResponse.Success(response.results))
                else emit(ApiResponse.Empty)
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.localizedMessage ?: e.toString()))
            }
        }.flowOn(Dispatchers.IO)

    fun searchGame(query: String) =
        flow {
            try {
                val response = apiService.searchGames(query)
                if (response.results.isNotEmpty()) emit(ApiResponse.Success(response.results))
                else emit(ApiResponse.Empty)
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getDetailGame(id: Int) =
        flow {
            try {
                val response = apiService.getGames(id)
                if (response.description?.isNotEmpty() == true) emit(ApiResponse.Success(response))
                else emit(ApiResponse.Empty)
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getGamesByDeveloper(developer: String) =
        flow {
            try {
                val response = apiService.getGamesByDeveloper(developer)
                if (response.results.isNotEmpty()) emit(ApiResponse.Success(response.results))
                else emit(ApiResponse.Empty)
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
}
