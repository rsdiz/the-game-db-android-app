package id.rsdiz.thegamedb.core.data.source.remote.response.games

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Class for testing [DevelopersResponse] class
 */
@RunWith(JUnit4::class)
class DevelopersResponseTest {

    /**
     * Test an equation for object symmetry
     */
    @Test
    fun testEqualsSymetric() {
        val developersResponse1 = DevelopersResponse(
            "Name",
            "slug"
        )
        val developersResponse2 = DevelopersResponse(
            "Name",
            "slug"
        )

        assertTrue(developersResponse1 == developersResponse2 && developersResponse2 == developersResponse1)
        assertTrue(developersResponse1.hashCode() == developersResponse2.hashCode())
    }
}
