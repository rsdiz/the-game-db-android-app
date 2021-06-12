package id.rsdiz.thegamedb.mobile.favorite.vm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import id.rsdiz.thegamedb.core.domain.model.Game
import id.rsdiz.thegamedb.core.domain.usecase.game.GameUseCase
import id.rsdiz.thegamedb.mobile.favorite.MainCoroutineScopeRule
import id.rsdiz.thegamedb.mobile.favorite.factory.GameFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

/**
 * Class for testing [FavoriteViewModel] class
 */
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class FavoriteViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    private val fakeGameList = GameFactory.makeGameList(10)
    private val favoritedGames = fakeGameList.filter { game -> game.isFavorite }
    private lateinit var favoriteViewModel: FavoriteViewModel

    @Mock
    private lateinit var gameUseCase: GameUseCase

    @Mock
    private lateinit var gamesObserver: Observer<List<Game>>

    @Captor
    private lateinit var captorGames: ArgumentCaptor<List<Game>>

    /**
     * Run before start testing
     */
    @Before
    fun setUp() {
        val flow: Flow<List<Game>> = flow { emit(favoritedGames) }

        Mockito.`when`(gameUseCase.getFavoriteGames()).thenReturn(flow)
        favoriteViewModel = FavoriteViewModel(gameUseCase)
        Mockito.verify(gameUseCase).getFavoriteGames()
    }

    /**
     * Function for testing [FavoriteViewModel.favoriteGames] variable
     */
    @Test
    fun getFavoriteGames() {
        val liveData = favoriteViewModel.favoriteGames
        liveData.observeForever(gamesObserver)

        Mockito.verify(gamesObserver).onChanged(captorGames.capture())
        assertNotNull(captorGames.value)
        assertEquals(favoritedGames, captorGames.value)
    }
}
