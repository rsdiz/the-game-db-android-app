package id.rsdiz.core.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import id.rsdiz.core.data.GameRepository
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(Singleton::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideGameRepository(repository: GameRepository): GameRepository
}
