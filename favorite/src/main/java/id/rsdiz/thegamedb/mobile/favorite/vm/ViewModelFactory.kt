package id.rsdiz.thegamedb.mobile.favorite.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.rsdiz.thegamedb.core.domain.usecase.game.GameUseCase
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class ViewModelFactory @Inject constructor(private val gameUseCase: GameUseCase) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> FavoriteViewModel(
                gameUseCase
            ) as T
            else -> throw Throwable("Unknown ViewModel class: ${modelClass.name}")
        }
}
