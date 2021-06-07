package id.rsdiz.thegamedb.mobile.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.rsdiz.thegamedb.core.domain.usecase.game.GameUseCase
import id.rsdiz.thegamedb.core.domain.usecase.game.IGameUseCase

@Module
@InstallIn(SingletonComponent::class)
abstract class MobileModule {
    @Binds
    abstract fun provideGameUseCase(gameRepository: GameUseCase): IGameUseCase
}
