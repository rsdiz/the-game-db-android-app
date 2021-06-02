package id.rsdiz.thegamedb.core.domain.usecase

import id.rsdiz.thegamedb.core.domain.model.Game
import id.rsdiz.thegamedb.core.domain.repository.IGameRepository
import javax.inject.Inject

class GameUseCase @Inject constructor(private val repository: IGameRepository) : IGameUseCase {
    override fun getGames() = repository.getGames()

    override fun getFavoriteGames() = repository.getFavoriteGames()

    override fun getDetailGame(id: Int) = repository.getDetailGame(id)

    override fun setFavoriteGame(game: Game) = repository.setFavoriteGame(game)

    override fun getAllDevelopers() = repository.getAllDevelopers()

    override fun getGamesByDeveloper(developer: String) = repository.getGamesByDeveloper(developer)

    override suspend fun searchGame(query: String) = repository.searchGame(query)

    override suspend fun insertGame(game: Game) = repository.insertGame(game)
}
