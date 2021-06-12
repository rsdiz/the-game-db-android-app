package id.rsdiz.thegamedb.core.data.source.remote.response.developer

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Class for testing [DeveloperResponse] class
 */
@RunWith(JUnit4::class)
class DeveloperResponseTest {

    /**
     * Test an equation for object symmetry
     */
    @Test
    fun testEqualsSymetric() {
        val developerResponse1 = DeveloperResponse(
            1,
            "Name",
            "slug",
            10,
            "Background",
            "Description"
        )
        val developerResponse2 = DeveloperResponse(
            1,
            "Name",
            "slug",
            10,
            "Background",
            "Description"
        )

        assertTrue(developerResponse1 == developerResponse2 && developerResponse2 == developerResponse1)
        assertTrue(developerResponse1.hashCode() == developerResponse2.hashCode())
    }
}
