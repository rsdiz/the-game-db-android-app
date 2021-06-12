package id.rsdiz.thegamedb.mobile.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import id.rsdiz.thegamedb.core.data.Resource
import id.rsdiz.thegamedb.core.domain.model.Game
import id.rsdiz.thegamedb.core.domain.usecase.game.GameUseCase
import id.rsdiz.thegamedb.mobile.MainCoroutineScopeRule
import id.rsdiz.thegamedb.mobile.factory.GameFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
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
 * Class for testing [DetailViewModel] class
 */
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    private val fakeGame = GameFactory.makeGame()
    private lateinit var detailViewModel: DetailViewModel

    @Mock
    private lateinit var gameUseCase: GameUseCase

    @Mock
    private lateinit var gamesObserver: Observer<Resource<Game>>

    @Captor
    private lateinit var captorGames: ArgumentCaptor<Resource<Game>>

    /**
     * Run before start testing
     */
    @Before
    fun setUp() {
        detailViewModel = DetailViewModel(gameUseCase)
    }

    /**
     * Function for testing [DetailViewModel.getDetailGame] method
     */
    @Test
    fun getDetailGame() = coroutineScope.runBlockingTest {
        val flow: Flow<Resource<Game>> = flow {
            emit(Resource.Loading())
            delay(10)
            emit(Resource.Success(fakeGame))
        }

        // Triggers the transformation
        Mockito.`when`(gameUseCase.getDetailGame(fakeGame.id)).thenReturn(flow)
        val result = detailViewModel.getDetailGame(fakeGame.id)
        Mockito.verify(gameUseCase).getDetailGame(fakeGame.id)
        result.observeForever(gamesObserver)

        // Received first state = [Resource.Loading]
        Mockito.verify(gamesObserver).onChanged(captorGames.capture())
        assertTrue(captorGames.value is Resource.Loading)

        coroutineScope.advanceTimeBy(10)

        // Received second state = [Resource.Success]
        Mockito.verify(gamesObserver, Mockito.times(2))
            .onChanged(captorGames.capture())
        assertEquals(fakeGame, captorGames.value.data)
    }
}
