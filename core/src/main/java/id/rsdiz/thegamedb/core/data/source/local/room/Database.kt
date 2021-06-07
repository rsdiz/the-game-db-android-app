package id.rsdiz.thegamedb.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import id.rsdiz.thegamedb.core.data.source.local.entity.DevelopersEntity
import id.rsdiz.thegamedb.core.data.source.local.entity.GameEntity

@Database(
    entities = [GameEntity::class, DevelopersEntity::class],
    version = 2,
    exportSchema = false
)
abstract class Database : RoomDatabase() {
    abstract fun gameDao(): IGameDao
    abstract fun developersDao(): IDevelopersDao
}
