package id.rsdiz.thegamedb.mobile.developer.vm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import id.rsdiz.thegamedb.core.data.Resource
import id.rsdiz.thegamedb.core.domain.model.Developers
import id.rsdiz.thegamedb.core.domain.model.Game
import id.rsdiz.thegamedb.core.domain.usecase.developers.DevelopersUseCase
import id.rsdiz.thegamedb.core.domain.usecase.game.GameUseCase
import id.rsdiz.thegamedb.mobile.developer.MainCoroutineScopeRule
import id.rsdiz.thegamedb.mobile.developer.factory.DevelopersFactory
import id.rsdiz.thegamedb.mobile.developer.factory.GameFactory
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
 * Class for testing [DeveloperViewModel] class
 */
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DeveloperViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    private val fakeDevList = DevelopersFactory.makeDevelopersList(10)
    private lateinit var developerViewModel: DeveloperViewModel

    @Mock
    private lateinit var gameUseCase: GameUseCase

    @Mock
    private lateinit var developersUseCase: DevelopersUseCase

    @Mock
    private lateinit var developersObserver: Observer<Resource<List<Developers>>>

    @Mock
    private lateinit var gameObserver: Observer<Resource<List<Game>>>

    @Captor
    private lateinit var captorGame: ArgumentCaptor<Resource<List<Game>>>

    @Captor
    private lateinit var captorDevelopers: ArgumentCaptor<Resource<List<Developers>>>

    @Mock
    private lateinit var developerObserver: Observer<Resource<Developers>>

    @Captor
    private lateinit var captorDeveloper: ArgumentCaptor<Resource<Developers>>

    /**
     * Run before start testing
     */
    @Before
    fun setUp() {
        val flow: Flow<Resource<List<Developers>>> = flow {
            emit(Resource.Loading())
            delay(10)
            emit(Resource.Success(fakeDevList))
        }

        Mockito.`when`(developersUseCase.getDevelopers()).thenReturn(flow)
        developerViewModel = DeveloperViewModel(gameUseCase, developersUseCase)
        Mockito.verify(developersUseCase).getDevelopers()
    }

    /**
     * Function for testing [DeveloperViewModel.getDevelopers] method
     */
    @Test
    fun getDevelopers() {
        val livedata = developerViewModel.developers
        livedata.observeForever(developersObserver)

        Mockito.verify(developersObserver).onChanged(captorDevelopers.capture())
        assertNotNull(captorDevelopers.value)
        assertTrue(captorDevelopers.value is Resource.Loading)

        coroutineScope.advanceTimeBy(10)

        Mockito.verify(developersObserver, Mockito.times(2))
            .onChanged(captorDevelopers.capture())
        assertNotNull(captorDevelopers.value)
        assertEquals(fakeDevList, captorDevelopers.value.data)
    }

    /**
     * Function for testing [DeveloperViewModel.getDetailDeveloper] method
     */
    @Test
    fun getDetailDeveloper() {
        val flow: Flow<Resource<Developers>> = flow {
            emit(Resource.Loading())
            delay(10)
            emit(Resource.Success(fakeDevList[5]))
        }

        Mockito.`when`(developersUseCase.getDetailDevelopers(fakeDevList[5].id)).thenReturn(flow)
        val liveData = developerViewModel.getDetailDeveloper(fakeDevList[5].id)
        Mockito.verify(developersUseCase).getDetailDevelopers(fakeDevList[5].id)
        liveData.observeForever(developerObserver)

        Mockito.verify(developerObserver).onChanged(captorDeveloper.capture())
        assertNotNull(captorDeveloper.value)
        assertTrue(captorDeveloper.value is Resource.Loading)

        coroutineScope.advanceTimeBy(10)

        Mockito.verify(developerObserver, Mockito.times(2))
            .onChanged(captorDeveloper.capture())
        assertNotNull(captorDeveloper.value)
        assertEquals(fakeDevList[5], captorDeveloper.value.data)
    }

    /**
     * Function for testing [DeveloperViewModel.getGameByDevelopers] method
     */
    @Test
    fun getGameByDevelopers() = coroutineScope.runBlockingTest {
        val fakeGame = GameFactory.makeGameList(10)
        val gameByDev1 = fakeGame.filter { game -> game.developers == fakeGame[0].developers }
        val resource: Resource<List<Game>> = Resource.Success(gameByDev1)

        Mockito.`when`(gameUseCase.getGamesByDeveloper(fakeGame[0].developers)).thenReturn(resource)
        developerViewModel.getGameByDevelopers(fakeGame[0].developers)
        Mockito.verify(gameUseCase).getGamesByDeveloper(fakeGame[0].developers)
        val liveData = developerViewModel.gameByDeveloperResult
        liveData.observeForever(gameObserver)

        Mockito.verify(gameObserver).onChanged(captorGame.capture())
        assertNotNull(captorGame.value)
        assertEquals(gameByDev1, captorGame.value.data)
    }
}
