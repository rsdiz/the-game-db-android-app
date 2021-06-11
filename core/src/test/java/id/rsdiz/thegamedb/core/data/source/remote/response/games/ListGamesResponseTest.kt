package id.rsdiz.thegamedb.core.data.source.remote.response.games

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ListGamesResponseTest {
    @Test
    fun testEqualsSymetric() {
        val listGameResponse1 = ListGamesResponse(
            2,
            "next-url-link",
            "previous-url-link",
            emptyList()
        )
        val listGameResponse2 = ListGamesResponse(
            2,
            "next-url-link",
            "previous-url-link",
            emptyList()
        )

        assertTrue(listGameResponse1 == listGameResponse2 && listGameResponse2 == listGameResponse1)
        assertTrue(listGameResponse1.hashCode() == listGameResponse2.hashCode())
    }
}
