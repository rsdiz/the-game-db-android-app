package id.rsdiz.thegamedb.core.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.rsdiz.thegamedb.core.data.GameRepository
import id.rsdiz.thegamedb.core.domain.repository.IGameRepository

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideGameRepository(repository: GameRepository): IGameRepository
}
