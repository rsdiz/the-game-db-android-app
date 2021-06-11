package id.rsdiz.thegamedb.core.data.source.remote.mapper

import id.rsdiz.thegamedb.core.data.source.local.entity.DevelopersEntity
import id.rsdiz.thegamedb.core.data.source.remote.response.developer.DeveloperResponse
import id.rsdiz.thegamedb.core.factory.DevelopersFactory
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class DevelopersRemoteMapperTest {
    private lateinit var developersRemoteMapper: DevelopersRemoteMapper

    @Before
    fun setUp() {
        developersRemoteMapper = DevelopersRemoteMapper()
    }

    @Test
    fun mapRemoteToEntity() {
        val developerRemote = DevelopersFactory.makeDevelopersResponse()
        val developerEntity = developersRemoteMapper.mapRemoteToEntity(developerRemote)

        assertDevelopersDataEquality(developerRemote, developerEntity)
    }

    @Test
    fun mapRemoteToEntities() {
        val developersRemoteList = DevelopersFactory.makeDevelopersResponseList(2)
        val developersEntities = developersRemoteMapper.mapRemoteToEntities(developersRemoteList)

        repeat(developersRemoteList.size) {
            assertDevelopersDataEquality(developersRemoteList[it], developersEntities[it])
        }
    }

    private fun assertDevelopersDataEquality(entity: DeveloperResponse, data: DevelopersEntity) {
        assertEquals(entity.id, data.id)
        assertEquals(entity.name, data.name)
        assertEquals(entity.slug, data.slug)
        assertEquals(entity.gamesCount, data.gamesCount)
        assertEquals(entity.imageBackground, data.imageBackground)
        assertEquals(entity.description, data.description)
    }
}
