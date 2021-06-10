package id.rsdiz.thegamedb.core.domain.usecase.developers

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.*
import id.rsdiz.thegamedb.core.MainCoroutineScopeRule
import id.rsdiz.thegamedb.core.data.Resource
import id.rsdiz.thegamedb.core.domain.model.Developers
import id.rsdiz.thegamedb.core.domain.repository.IDevelopersRepository
import id.rsdiz.thegamedb.core.factory.DevelopersFactory
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
import org.mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DevelopersUseCaseTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    private val fakeDevelopersList = DevelopersFactory.makeDevelopersList(4)
    private val fakeDevelopers = fakeDevelopersList[0]
    private lateinit var developersUseCase: DevelopersUseCase

    @Mock
    private lateinit var developerRepository: IDevelopersRepository

    @Mock
    private lateinit var developersObserver: Observer<Resource<List<Developers>>>

    @Mock
    private lateinit var developerObserver: Observer<Resource<Developers>>

    @Captor
    private lateinit var captorDevelopers: ArgumentCaptor<Resource<List<Developers>>>

    @Captor
    private lateinit var captorDeveloper: ArgumentCaptor<Resource<Developers>>

    @Before
    fun setUp() {
        developersUseCase = DevelopersUseCase(developerRepository)
    }

    @Test
    fun getDevelopers() = coroutineScope.runBlockingTest {
        val flow: Flow<Resource<List<Developers>>> = flow {
            emit(Resource.Loading())
            delay(10)
            emit(Resource.Success(fakeDevelopersList))
        }

        // Triggers the transformation
        Mockito.`when`(developerRepository.getDevelopers()).thenReturn(flow)
        val developersList = developerRepository.getDevelopers().asLiveData()
        developersList.observeForever(developersObserver)

        // Received first state = [Resource.Loading]
        Mockito.verify(developersObserver).onChanged(captorDevelopers.capture())
        assertEquals(true, captorDevelopers.value is Resource.Loading)

        coroutineScope.advanceTimeBy(10)

        // Received second state = Resource.Success
        Mockito.verify(developersObserver, Mockito.times(2))
            .onChanged(captorDevelopers.capture())
        assertEquals(
            fakeDevelopersList,
            captorDevelopers.value.data
        )
    }

    @Test
    fun getDetailDevelopers() = coroutineScope.runBlockingTest {
        val flow: Flow<Resource<Developers>> = flow {
            emit(Resource.Loading())
            delay(10)
            emit(Resource.Success(fakeDevelopers))
        }

        // Triggers the transformation
        Mockito.`when`(developerRepository.getDetailDevelopers(fakeDevelopers.id))
            .thenReturn(flow)
        val developers = developerRepository.getDetailDevelopers(fakeDevelopers.id).asLiveData()
        developers.observeForever(developerObserver)

        // Received first state = [Resource.Loading]
        Mockito.verify(developerObserver).onChanged(captorDeveloper.capture())
        assertEquals(true, captorDeveloper.value is Resource.Loading)

        coroutineScope.advanceTimeBy(10)

        // Received second state = [Resource.Success]
        Mockito.verify(developerObserver, Mockito.times(2))
            .onChanged(captorDeveloper.capture())
        assertEquals(
            fakeDevelopers,
            captorDeveloper.value.data
        )
    }
}
