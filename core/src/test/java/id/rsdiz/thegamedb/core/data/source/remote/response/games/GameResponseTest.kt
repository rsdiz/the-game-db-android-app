package id.rsdiz.thegamedb.core.data.source.remote.response.games

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GameResponseTest {
    @Test
    fun testEqualsSymetric() {
        val gameResponse1 = GameResponse(
            1,
            "Name",
            "Description",
            "2021-11-06",
            "Background",
            "github.com",
            5.0f,
            10,
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList()
        )
        val gameResponse2 = GameResponse(
            1,
            "Name",
            "Description",
            "2021-11-06",
            "Background",
            "github.com",
            5.0f,
            10,
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList()
        )

        assertTrue(gameResponse1 == gameResponse2 && gameResponse2 == gameResponse1)
        assertTrue(gameResponse1.hashCode() == gameResponse2.hashCode())
    }
}
