package id.rsdiz.thegamedb.core.data.source.local.mapper

import id.rsdiz.thegamedb.core.data.source.local.entity.DevelopersEntity
import id.rsdiz.thegamedb.core.domain.model.Developers
import id.rsdiz.thegamedb.core.factory.DevelopersFactory
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class DevelopersMapperTest {
    private lateinit var developersMapper: DevelopersMapper

    @Before
    fun setUp() {
        developersMapper = DevelopersMapper()
    }

    @Test
    fun mapFromEntity() {
        val developersEntity = DevelopersFactory.makeDevelopersEntity()
        val developers = developersMapper.mapFromEntity(developersEntity)

        assertDevelopersDataEquality(developersEntity, developers)
    }

    @Test
    fun mapToEntity() {
        val developers = DevelopersFactory.makeDevelopers()
        val developersEntity = developersMapper.mapToEntity(developers)

        assertDevelopersDataEquality(developersEntity, developers)
    }

    @Test
    fun mapFromEntities() {
        val developersEntities = DevelopersFactory.makeDevelopersEntities(2)
        val developersList = developersMapper.mapFromEntities(developersEntities)

        repeat(developersEntities.size) {
            assertDevelopersDataEquality(developersEntities[it], developersList[it])
        }
    }

    private fun assertDevelopersDataEquality(entity: DevelopersEntity, data: Developers) {
        assertEquals(entity.id, data.id)
        assertEquals(entity.name, data.name)
        assertEquals(entity.slug, data.slug)
        assertEquals(entity.gamesCount, data.gamesCount)
        assertEquals(entity.imageBackground, data.imageBackground)
        assertEquals(entity.description, data.description)
    }
}
