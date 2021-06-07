package id.rsdiz.thegamedb.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Query
import id.rsdiz.thegamedb.core.data.source.local.entity.DevelopersEntity
import id.rsdiz.thegamedb.core.data.source.local.room.base.IBaseDao
import kotlinx.coroutines.flow.Flow

@Dao
interface IDevelopersDao : IBaseDao<DevelopersEntity> {
    @Query("SELECT * FROM developers")
    fun getAllDevelopers(): Flow<List<DevelopersEntity>>

    @Query("SELECT * FROM developers WHERE id = :id")
    fun getDevelopersById(id: Int): Flow<DevelopersEntity>?
}
