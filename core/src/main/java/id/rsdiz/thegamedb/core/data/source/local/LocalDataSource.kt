package id.rsdiz.thegamedb.core.data.source.local

import id.rsdiz.thegamedb.core.data.source.local.entity.GameEntity
import id.rsdiz.thegamedb.core.data.source.local.room.IGameDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val gameDao: IGameDao) {
    fun getAllGames() = gameDao.getAllGames()

    fun getFavoriteGames() = gameDao.getFavoriteGames()

    fun getGameById(id: Int) = gameDao.getGameById(id)

    fun getDevelopersList() = gameDao.getAllDevelopers()

    fun getListGameByDeveloper(developerName: String) = gameDao.getGamesByDeveloper(developerName)

    fun updateGame(data: GameEntity) = gameDao.update(data)

    suspend fun insertGame(data: GameEntity) = gameDao.insert(data)

    suspend fun insertAllGame(list: List<GameEntity>) = gameDao.insertAll(list)
}
