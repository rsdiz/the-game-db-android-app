package id.rsdiz.thegamedb.core.data.source.remote.response.games

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ParentPlatformsResponseTest {
    @Test
    fun testEqualsSymetric() {
        val platformResponse1 = PlatformResponse("name1")

        val parentPlatformsResponse1 = ParentPlatformsResponse(platformResponse1)
        val parentPlatformsResponse2 = ParentPlatformsResponse(platformResponse1)

        assertTrue(parentPlatformsResponse1 == parentPlatformsResponse2 && parentPlatformsResponse2 == parentPlatformsResponse1)
        assertTrue(parentPlatformsResponse1.hashCode() == parentPlatformsResponse2.hashCode())
    }
}
