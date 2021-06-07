package id.rsdiz.thegamedb.core.data.source.local

import id.rsdiz.thegamedb.core.data.source.local.entity.GameEntity
import id.rsdiz.thegamedb.core.data.source.local.mapper.GameMapper
import id.rsdiz.thegamedb.core.data.source.local.room.IGameDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameLocalDataSource @Inject constructor(
    private val gameDao: IGameDao,
    val mapper: GameMapper
) {
    fun getAllGames() = gameDao.getAllGames()

    fun getFavoriteGames() = gameDao.getFavoriteGames()

    fun getGameById(id: Int) = gameDao.getGameById(id)

    fun getListGameByDeveloper(developerName: String) = gameDao.getGamesByDeveloper(developerName)

    fun update(data: GameEntity) = gameDao.update(data)

    suspend fun insert(data: GameEntity) = gameDao.insert(data)

    suspend fun insertAll(list: List<GameEntity>) = gameDao.insertAll(list)
}
