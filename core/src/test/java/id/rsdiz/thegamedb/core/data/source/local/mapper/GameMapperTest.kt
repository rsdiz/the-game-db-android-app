package id.rsdiz.thegamedb.core.data.source.local.mapper

import id.rsdiz.thegamedb.core.data.source.local.entity.GameEntity
import id.rsdiz.thegamedb.core.domain.model.Game
import id.rsdiz.thegamedb.core.factory.GameFactory
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Class for testing [GameMapper] class
 */
@RunWith(JUnit4::class)
class GameMapperTest {
    private lateinit var gameMapper: GameMapper

    /**
     * Run before start testing
     */
    @Before
    fun setUp() {
        gameMapper = GameMapper()
    }

    /**
     * Function for testing [GameMapper.mapFromEntity] method
     */
    @Test
    fun mapFromEntity() {
        val gameEntity = GameFactory.makeGameEntity()
        val game = gameMapper.mapFromEntity(gameEntity)

        assertGameDataEquality(gameEntity, game)
    }

    /**
     * Function for testing [GameMapper.mapToEntity] method
     */
    @Test
    fun mapToEntity() {
        val game = GameFactory.makeGame()
        val gameEntity = gameMapper.mapToEntity(game)

        assertGameDataEquality(gameEntity, game)
    }

    /**
     * Function for testing [GameMapper.mapFromEntities] method
     */
    @Test
    fun mapFromEntities() {
        val gameEntities = GameFactory.makeGameEntities(2)
        val gameList = gameMapper.mapFromEntities(gameEntities)

        repeat(gameEntities.size) {
            assertGameDataEquality(gameEntities[it], gameList[it])
        }
    }

    private fun assertGameDataEquality(entity: GameEntity, data: Game) {
        assertEquals(entity.id, data.id)
        assertEquals(entity.name, data.name)
        assertEquals(entity.backgroundImage, data.backgroundImage)
        assertEquals(entity.rating, data.rating)
        assertEquals(entity.parentPlatforms, data.parentPlatforms)
        assertEquals(entity.genres, data.genres)
        assertEquals(entity.released, data.released)
        assertEquals(entity.websiteUrl, data.websiteUrl)
        assertEquals(entity.playtime, data.playtime)
        assertEquals(entity.platforms, data.platforms)
        assertEquals(entity.developers, data.developers)
        assertEquals(entity.description, data.description)
        assertEquals(entity.isFavorite, data.isFavorite)
    }
}
