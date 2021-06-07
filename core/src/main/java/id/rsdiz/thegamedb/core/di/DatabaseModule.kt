package id.rsdiz.thegamedb.core.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.rsdiz.thegamedb.core.data.source.local.room.Database
import id.rsdiz.thegamedb.core.utils.Const
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): Database {
        val passphrase = SQLiteDatabase.getBytes(Const.PASSPHRASE.toCharArray())
        val factory = SupportFactory(passphrase)

        return Room.databaseBuilder(context, Database::class.java, Const.DB_NAME)
            .fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }

    @Provides
    fun provideGameDao(database: Database) = database.gameDao()

    @Provides
    fun provideDevelopersDao(database: Database) = database.developersDao()
}
