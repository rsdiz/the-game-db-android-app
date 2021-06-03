package id.rsdiz.thegamedb.mobile.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import id.rsdiz.thegamedb.core.domain.usecase.GameUseCase
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(gameUseCase: GameUseCase) : ViewModel() {
    val games = gameUseCase.getGames().asLiveData()
}
