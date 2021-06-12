package id.rsdiz.thegamedb.core.data.source.local.entity

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Class for testing [GameEntity] class
 */
@RunWith(JUnit4::class)
class GameEntityTest {

    /**
     * Test an equation for object symmetry
     */
    @Test
    fun testEqualsSymetric() {
        val game1 = GameEntity(
            1,
            "Name",
            "Background",
            5.0f,
            "ParentPlatform",
            "Genres",
            "2021-11-06",
            "github.com",
            10,
            "Platforms",
            "Developers",
            "Description",
            true
        )
        val game2 = GameEntity(
            1,
            "Name",
            "Background",
            5.0f,
            "ParentPlatform",
            "Genres",
            "2021-11-06",
            "github.com",
            10,
            "Platforms",
            "Developers",
            "Description",
            true
        )

        assertTrue(game1 == game2 && game2 == game1)
        assertTrue(game1.hashCode() == game2.hashCode())
    }
}
