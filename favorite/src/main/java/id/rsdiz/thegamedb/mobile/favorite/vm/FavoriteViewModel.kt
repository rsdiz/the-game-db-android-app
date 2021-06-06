package id.rsdiz.thegamedb.mobile.favorite.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.rsdiz.thegamedb.core.domain.usecase.GameUseCase

class FavoriteViewModel(gameUseCase: GameUseCase) : ViewModel() {
    val favoriteGames = gameUseCase.getFavoriteGames().asLiveData()
}
