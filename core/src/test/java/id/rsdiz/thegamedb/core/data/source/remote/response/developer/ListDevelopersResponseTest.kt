package id.rsdiz.thegamedb.core.data.source.remote.response.developer

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ListDevelopersResponseTest {
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
            2,
            "Name2",
            "slug2",
            20,
            "Background2",
            "Description2"
        )

        val listDevelopersResponse1 = listOf(
            developerResponse1,
            developerResponse2
        )

        val listDevelopersResponse2 = listOf(
            developerResponse1,
            developerResponse2
        )

        assertTrue(listDevelopersResponse1 == listDevelopersResponse2 && listDevelopersResponse2 == listDevelopersResponse1)
        assertTrue(listDevelopersResponse1.hashCode() == listDevelopersResponse2.hashCode())
    }
}
