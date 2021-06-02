package id.rsdiz.thegamedb.core.data.source.remote.network

import id.rsdiz.thegamedb.core.data.source.remote.response.GameResponse
import id.rsdiz.thegamedb.core.data.source.remote.response.ListGamesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("games")
    suspend fun getListGames(): ListGamesResponse

    @GET("games")
    suspend fun searchGames(@Query("search") search: String): ListGamesResponse

    @GET("games/{id}")
    suspend fun getGames(@Path("id") Id: Int): GameResponse

    @GET("games")
    suspend fun getGamesByDeveloper(@Query("developers") developers: String): ListGamesResponse
}
