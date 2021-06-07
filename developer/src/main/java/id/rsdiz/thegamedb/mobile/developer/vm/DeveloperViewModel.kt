package id.rsdiz.thegamedb.mobile.developer.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.rsdiz.thegamedb.core.data.Resource
import id.rsdiz.thegamedb.core.domain.model.Game
import id.rsdiz.thegamedb.core.domain.usecase.developers.DevelopersUseCase
import id.rsdiz.thegamedb.core.domain.usecase.game.GameUseCase

class DeveloperViewModel(
    private val gameUseCase: GameUseCase,
    private val developersUseCase: DevelopersUseCase
) : ViewModel() {
    val developers = developersUseCase.getDevelopers().asLiveData()

    private var _gameByDeveloperResult = MutableLiveData<Resource<List<Game>>>()
    val gameByDeveloperResult: LiveData<Resource<List<Game>>> get() = _gameByDeveloperResult

    fun getDetailDeveloper(id: Int) = developersUseCase.getDetailDevelopers(id).asLiveData()

    suspend fun getGameByDevelopers(slug: String) {
        _gameByDeveloperResult.value = gameUseCase.getGamesByDeveloper(slug)
    }

    suspend fun insertGame(game: Game) = gameUseCase.insertGame(game)
}
