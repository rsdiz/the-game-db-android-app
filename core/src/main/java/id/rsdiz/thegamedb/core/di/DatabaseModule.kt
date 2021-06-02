package id.rsdiz.thegamedb.core.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.rsdiz.thegamedb.core.data.source.local.room.GameDatabase
import id.rsdiz.thegamedb.core.utils.Const
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): GameDatabase =
        Room.databaseBuilder(context, GameDatabase::class.java, Const.DB_NAME)
            .fallbackToDestructiveMigration().build()

    @Provides
    fun provideGameDao(database: GameDatabase) = database.gameDao()
}
