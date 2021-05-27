package id.rsdiz.core.domain.usecase

import id.rsdiz.core.data.Resource
import id.rsdiz.core.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface IGameUseCase {
    fun getGames(): Flow<Resource<List<Game>>>

    fun getFavoriteGames(): Flow<List<Game>>

    fun getDetailGame(id: Int): Flow<Resource<Game>>

    fun setFavoriteGame(game: Game)

    fun getAllDevelopers(): Flow<List<String>>

    fun getGamesByDeveloper(developer: String): Flow<Resource<List<Game>>>

    suspend fun searchGame(query: String): Resource<List<Game>>

    suspend fun insertGame(game: Game)
}
