package id.rsdiz.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Query
import id.rsdiz.core.data.source.local.entity.GameEntity
import id.rsdiz.core.data.source.local.room.base.IBaseDao
import kotlinx.coroutines.flow.Flow

@Dao
interface IGameDao : IBaseDao<GameEntity> {
    @Query("SELECT * FROM games")
    fun getAllGames(): Flow<List<GameEntity>>

    @Query("SELECT * FROM games WHERE isFavorite = 1")
    fun getFavoriteGames(): Flow<List<GameEntity>>

    @Query("SELECT * FROM games WHERE id = :id")
    fun getGameById(id: Int): Flow<GameEntity>?

    @Query("SELECT developers FROM games")
    fun getAllDevelopers(): Flow<List<String>>

    @Query("SELECT * FROM games WHERE developers = :developer")
    fun getGamesByDeveloper(developer: String): Flow<List<GameEntity>>
}
