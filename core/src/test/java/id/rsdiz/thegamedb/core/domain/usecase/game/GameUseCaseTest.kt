package id.rsdiz.thegamedb.core.domain.usecase.game

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import id.rsdiz.thegamedb.core.MainCoroutineScopeRule
import id.rsdiz.thegamedb.core.data.Resource
import id.rsdiz.thegamedb.core.domain.model.Game
import id.rsdiz.thegamedb.core.domain.repository.IGameRepository
import id.rsdiz.thegamedb.core.factory.GameFactory
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

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GameUseCaseTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    private val fakeGameList = GameFactory.makeGameList(10)
    private val fakeGame = fakeGameList[0]
    private lateinit var gameUseCase: GameUseCase

    @Mock
    private lateinit var gameRepository: IGameRepository

    @Mock
    private lateinit var gamesObserver: Observer<Resource<List<Game>>>

    @Mock
    private lateinit var gameObserver: Observer<Resource<Game>>

    @Mock
    private lateinit var gamesObserverList: Observer<List<Game>>

    @Captor
    private lateinit var captorGames: ArgumentCaptor<Resource<List<Game>>>

    @Captor
    private lateinit var captorGame: ArgumentCaptor<Resource<Game>>

    @Captor
    private lateinit var captorGamesList: ArgumentCaptor<List<Game>>

    @Before
    fun setUp() {
        gameUseCase = GameUseCase(gameRepository)
    }

    @Test
    fun getGames() = coroutineScope.runBlockingTest {
        val flow: Flow<Resource<List<Game>>> = flow {
            emit(Resource.Loading())
            delay(10)
            emit(Resource.Success(fakeGameList))
        }

        // Triggers the transformation
        Mockito.`when`(gameRepository.getGames()).thenReturn(flow)
        val gameList = gameRepository.getGames().asLiveData()
        gameList.observeForever(gamesObserver)

        // Received first state = [Resource.Loading]
        Mockito.verify(gamesObserver).onChanged(captorGames.capture())
        assertEquals(true, captorGames.value is Resource.Loading)

        coroutineScope.advanceTimeBy(10)

        // Received second state = Resource.Success
        Mockito.verify(gamesObserver, Mockito.times(2))
            .onChanged(captorGames.capture())
        assertEquals(
            fakeGameList,
            captorGames.value.data
        )
    }

    @Test
    fun getFavoriteGames() = coroutineScope.runBlockingTest {
        val favoritedGame = fakeGameList.filter { game -> game.isFavorite }
        val flow: Flow<List<Game>> = flow { emit(favoritedGame) }

        // Triggers the transformation
        Mockito.`when`(gameRepository.getFavoriteGames()).thenReturn(flow)
        val gameList = gameRepository.getFavoriteGames().asLiveData()
        gameList.observeForever(gamesObserverList)

        // Received first state = [Resource.Loading]
        Mockito.verify(gamesObserverList).onChanged(captorGamesList.capture())
        assertEquals(
            favoritedGame,
            captorGamesList.value
        )
    }

    @Test
    fun getDetailGame() = coroutineScope.runBlockingTest {
        val flow: Flow<Resource<Game>> = flow {
            emit(Resource.Loading())
            delay(10)
            emit(Resource.Success(fakeGame))
        }

        // Triggers the transformation
        Mockito.`when`(gameRepository.getDetailGame(fakeGame.id)).thenReturn(flow)
        val gameList = gameRepository.getDetailGame(fakeGame.id).asLiveData()
        gameList.observeForever(gameObserver)

        // Received first state = [Resource.Loading]
        Mockito.verify(gameObserver).onChanged(captorGame.capture())
        assertEquals(true, captorGame.value is Resource.Loading)

        coroutineScope.advanceTimeBy(10)

        // Received second state = Resource.Success
        Mockito.verify(gameObserver, Mockito.times(2))
            .onChanged(captorGame.capture())
        assertEquals(
            fakeGame,
            captorGame.value.data
        )
    }

    @Test
    fun getGamesByDeveloper() = coroutineScope.runBlockingTest {
        val developer = fakeGame.developers
        val resultGame = fakeGameList.filter { game -> game.developers == developer }
        val resource: Resource<List<Game>> = Resource.Success(resultGame)

        Mockito.`when`(gameRepository.getGamesByDeveloper(developer)).thenReturn(resource)
        val gameList = gameRepository.getGamesByDeveloper(developer).data
        assertEquals(
            resultGame,
            gameList
        )
    }
}
