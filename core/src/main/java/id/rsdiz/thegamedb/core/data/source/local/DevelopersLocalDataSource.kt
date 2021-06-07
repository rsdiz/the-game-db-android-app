package id.rsdiz.thegamedb.core.data.source.local

import id.rsdiz.thegamedb.core.data.source.local.entity.DevelopersEntity
import id.rsdiz.thegamedb.core.data.source.local.mapper.DevelopersMapper
import id.rsdiz.thegamedb.core.data.source.local.room.IDevelopersDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DevelopersLocalDataSource @Inject constructor(
    private val developersDao: IDevelopersDao,
    val mapper: DevelopersMapper
) {
    fun getAllDevelopers() = developersDao.getAllDevelopers()

    fun getDevelopersById(id: Int) = developersDao.getDevelopersById(id)

    suspend fun insert(data: DevelopersEntity) = developersDao.insert(data)

    suspend fun insertAll(list: List<DevelopersEntity>) = developersDao.insertAll(list)
}
