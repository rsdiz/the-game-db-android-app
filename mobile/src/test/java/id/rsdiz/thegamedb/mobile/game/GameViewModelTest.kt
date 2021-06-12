package id.rsdiz.thegamedb.mobile.game

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
 * Class for testing [GameViewModel] class
 */
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GameViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    private val fakeGameList = GameFactory.makeGameList(10)
    private val fakeGame1 = fakeGameList[0]
    private val fakeGame2 = fakeGameList[5]
    private lateinit var gameViewModel: GameViewModel

    @Mock
    private lateinit var gameUseCase: GameUseCase

    @Mock
    private lateinit var gamesObserver: Observer<Resource<List<Game>>>

    @Captor
    private lateinit var captorGames: ArgumentCaptor<Resource<List<Game>>>

    /**
     * Run before start testing
     */
    @Before
    fun setUp() {
        val flow: Flow<Resource<List<Game>>> = flow {
            emit(Resource.Loading())
            delay(10)
            emit(Resource.Success(fakeGameList))
        }

        // Triggers the transformation
        Mockito.`when`(gameUseCase.getGames()).thenReturn(flow)
        gameViewModel = GameViewModel(gameUseCase)
        Mockito.verify(gameUseCase).getGames()
    }

    /**
     * Function for testing [GameViewModel.games] variable
     */
    @Test
    fun getGames() = coroutineScope.runBlockingTest {
        val gameList = gameViewModel.games
        gameList.observeForever(gamesObserver)

        // Received first state = [Resource.Loading]
        Mockito.verify(gamesObserver).onChanged(captorGames.capture())
        assertTrue(captorGames.value is Resource.Loading)

        coroutineScope.advanceTimeBy(10)

        // Received second state = [Resource.Success]
        Mockito.verify(gamesObserver, Mockito.times(2))
            .onChanged(captorGames.capture())
        assertEquals(fakeGameList, captorGames.value.data)
    }

    /**
     * Function for testing [GameViewModel.searchGames] method
     */
    @Test
    fun searchGames() = coroutineScope.runBlockingTest {
        var expected = fakeGameList.filter { game -> game == fakeGame1 }
        var value = Resource.Success(expected)

        Mockito.`when`(gameUseCase.searchGame(fakeGame1.name)).thenReturn(value)
        gameViewModel.searchGames(fakeGame1.name)
        Mockito.verify(gameUseCase).searchGame(fakeGame1.name)

        var result = gameViewModel.searchResult
        assertEquals(expected, result.value?.data)

        expected = fakeGameList.filter { game -> game == fakeGame2 }
        value = Resource.Success(expected)

        Mockito.`when`(gameUseCase.searchGame(fakeGame2.name)).thenReturn(value)
        gameViewModel.searchGames(fakeGame2.name)
        Mockito.verify(gameUseCase).searchGame(fakeGame2.name)

        result = gameViewModel.searchResult
        assertEquals(expected, result.value?.data)
    }
}
