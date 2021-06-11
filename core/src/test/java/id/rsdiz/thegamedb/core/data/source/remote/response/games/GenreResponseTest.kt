package id.rsdiz.thegamedb.core.data.source.remote.response.games

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GenreResponseTest {
    @Test
    fun testEqualsSymetric() {
        val genreResponse1 = GenreResponse(
            1,
            "Name",
            "slug"
        )
        val genreResponse2 = GenreResponse(
            1,
            "Name",
            "slug"
        )

        assertTrue(genreResponse1 == genreResponse2 && genreResponse2 == genreResponse1)
        assertTrue(genreResponse1.hashCode() == genreResponse2.hashCode())
    }
}
