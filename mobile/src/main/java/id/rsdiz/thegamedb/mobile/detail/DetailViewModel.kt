package id.rsdiz.thegamedb.mobile.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import id.rsdiz.thegamedb.core.domain.model.Game
import id.rsdiz.thegamedb.core.domain.usecase.GameUseCase
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val gameUseCase: GameUseCase) : ViewModel() {
    fun getDetailGame(gameId: Int) = gameUseCase.getDetailGame(gameId).asLiveData()
    fun setFavoriteGame(game: Game) = gameUseCase.setFavoriteGame(game)
}
