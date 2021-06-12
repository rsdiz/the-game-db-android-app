package id.rsdiz.thegamedb.core.data.source.remote.mapper

import id.rsdiz.thegamedb.core.data.source.local.entity.GameEntity
import id.rsdiz.thegamedb.core.data.source.remote.response.games.GameResponse
import id.rsdiz.thegamedb.core.factory.GameFactory
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Class for testing [GameRemoteMapper] class
 */
@RunWith(JUnit4::class)
class GameRemoteMapperTest {
    private lateinit var gameRemoteMapper: GameRemoteMapper

    /**
     * Run before start testing
     */
    @Before
    fun setUp() {
        gameRemoteMapper = GameRemoteMapper()
    }

    /**
     * Function for testing [GameRemoteMapper.mapRemoteToEntity] method
     */
    @Test
    fun mapRemoteToEntity() {
        val gameRemote = GameFactory.makeGameResponse()
        val gameEntity = gameRemoteMapper.mapRemoteToEntity(gameRemote)

        assertGameDataEquality(gameRemote, gameEntity)
    }

    /**
     * Function for testing [GameRemoteMapper.mapRemoteToEntities] method
     */
    @Test
    fun mapRemoteToEntities() {
        val gameRemoteList = GameFactory.makeGameResponseList(2)
        val gameEntities = gameRemoteMapper.mapRemoteToEntities(gameRemoteList)

        repeat(gameRemoteList.size) {
            assertGameDataEquality(gameRemoteList[it], gameEntities[it])
        }
    }

    private fun assertGameDataEquality(entity: GameResponse, data: GameEntity) {
        assertEquals(entity.id, data.id)
        assertEquals(entity.name, data.name)
        assertEquals(entity.backgroundImage, data.backgroundImage)
        assertEquals(entity.rating, data.rating)
        assertEquals(entity.released, data.released)
        assertEquals(entity.websiteUrl, data.websiteUrl)
        assertEquals(entity.playtime, data.playtime)
    }
}
