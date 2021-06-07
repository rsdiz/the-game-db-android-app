package id.rsdiz.thegamedb.mobile.di

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.rsdiz.thegamedb.core.domain.usecase.game.GameUseCase

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteModule {
    fun gameUseCase(): GameUseCase
}
