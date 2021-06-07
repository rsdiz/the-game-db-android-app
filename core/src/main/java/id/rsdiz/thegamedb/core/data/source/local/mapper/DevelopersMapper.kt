package id.rsdiz.thegamedb.core.data.source.local.mapper

import id.rsdiz.thegamedb.core.data.source.local.entity.DevelopersEntity
import id.rsdiz.thegamedb.core.data.source.local.mapper.base.DataMapper
import id.rsdiz.thegamedb.core.domain.model.Developers
import javax.inject.Inject

/**
 * Mapper for developers entity from data to domain layer
 */
open class DevelopersMapper @Inject constructor() : DataMapper<DevelopersEntity, Developers> {
    override fun mapFromEntity(entity: DevelopersEntity): Developers =
        Developers(
            id = entity.id,
            name = entity.name,
            slug = entity.slug,
            gamesCount = entity.gamesCount,
            imageBackground = entity.imageBackground,
            description = entity.description
        )

    override fun mapToEntity(model: Developers): DevelopersEntity =
        DevelopersEntity(
            id = model.id,
            name = model.name,
            slug = model.slug,
            gamesCount = model.gamesCount,
            imageBackground = model.imageBackground,
            description = model.description
        )

    override fun mapFromEntities(entities: List<DevelopersEntity>): List<Developers> =
        entities.map {
            Developers(
                id = it.id,
                name = it.name,
                slug = it.slug,
                gamesCount = it.gamesCount,
                imageBackground = it.imageBackground,
                description = it.description
            )
        }
}
