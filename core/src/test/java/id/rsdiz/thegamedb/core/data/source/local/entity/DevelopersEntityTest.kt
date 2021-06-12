package id.rsdiz.thegamedb.core.data.source.local.entity

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Class for testing [DevelopersEntity] class
 */
@RunWith(JUnit4::class)
class DevelopersEntityTest {

    /**
     * Test an equation for object symmetry
     */
    @Test
    fun testEqualsSymetric() {
        val developers1 = DevelopersEntity(
            1,
            "Name",
            "slug",
            100,
            "Background",
            "Description"
        )
        val developers2 = DevelopersEntity(
            1,
            "Name",
            "slug",
            100,
            "Background",
            "Description"
        )

        assertTrue(developers1 == developers2 && developers2 == developers1)
        assertTrue(developers1.hashCode() == developers2.hashCode())
    }
}
