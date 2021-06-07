package id.rsdiz.thegamedb.core.domain.usecase.game

import id.rsdiz.thegamedb.core.data.Resource
import id.rsdiz.thegamedb.core.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface IGameUseCase {
    fun getGames(): Flow<Resource<List<Game>>>

    fun getFavoriteGames(): Flow<List<Game>>

    fun getDetailGame(id: Int): Flow<Resource<Game>>

    fun setFavoriteGame(game: Game)

    suspend fun getGamesByDeveloper(developer: String): Resource<List<Game>>

    suspend fun searchGame(query: String): Resource<List<Game>>

    suspend fun insertGame(game: Game)
}
