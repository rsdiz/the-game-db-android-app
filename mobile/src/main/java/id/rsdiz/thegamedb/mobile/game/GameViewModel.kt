package id.rsdiz.thegamedb.mobile.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import id.rsdiz.thegamedb.core.data.Resource
import id.rsdiz.thegamedb.core.domain.model.Game
import id.rsdiz.thegamedb.core.domain.usecase.game.GameUseCase
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(private val gameUseCase: GameUseCase) : ViewModel() {
    val games = gameUseCase.getGames().asLiveData()

    private var _searchResult = MutableLiveData<Resource<List<Game>>>()
    val searchResult: LiveData<Resource<List<Game>>> get() = _searchResult

    suspend fun searchGames(query: String) {
        _searchResult.value = gameUseCase.searchGame(query)
    }

    suspend fun insertGame(game: Game) = gameUseCase.insertGame(game)
}
