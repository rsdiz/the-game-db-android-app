package id.rsdiz.thegamedb.core.data.source.remote.response.games

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Class for testing [PlatformsResponse] class
 */
@RunWith(JUnit4::class)
class PlatformsResponseTest {

    /**
     * Test an equation for object symmetry
     */
    @Test
    fun testEqualsSymetric() {
        val platformResponse1 = PlatformResponse("Name")
        val platformResponse2 = PlatformResponse("Name")

        assertTrue(platformResponse1 == platformResponse2 && platformResponse2 == platformResponse1)
        assertTrue(platformResponse1.hashCode() == platformResponse2.hashCode())

        val platformsResponse1 = PlatformsResponse(platformResponse1)
        val platformsResponse2 = PlatformsResponse(platformResponse1)

        assertTrue(platformsResponse1 == platformsResponse2 && platformsResponse2 == platformsResponse1)
        assertTrue(platformsResponse1.hashCode() == platformsResponse2.hashCode())
    }
}
