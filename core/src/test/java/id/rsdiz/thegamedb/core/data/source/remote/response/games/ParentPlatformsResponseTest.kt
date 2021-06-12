package id.rsdiz.thegamedb.core.data.source.remote.response.games

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Class for testing [ParentPlatformsResponse] class
 */
@RunWith(JUnit4::class)
class ParentPlatformsResponseTest {

    /**
     * Test an equation for object symmetry
     */
    @Test
    fun testEqualsSymetric() {
        val platformResponse1 = PlatformResponse("name1")

        val parentPlatformsResponse1 = ParentPlatformsResponse(platformResponse1)
        val parentPlatformsResponse2 = ParentPlatformsResponse(platformResponse1)

        assertTrue(parentPlatformsResponse1 == parentPlatformsResponse2 && parentPlatformsResponse2 == parentPlatformsResponse1)
        assertTrue(parentPlatformsResponse1.hashCode() == parentPlatformsResponse2.hashCode())
    }
}
