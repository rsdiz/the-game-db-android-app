package id.rsdiz.thegamedb.core.data.source.local.mapper

import id.rsdiz.thegamedb.core.data.source.local.entity.GameEntity
import id.rsdiz.thegamedb.core.data.source.local.mapper.base.DataMapper
import id.rsdiz.thegamedb.core.domain.model.Game
import javax.inject.Inject

/**
 * Mapper for game entity from data to domain layer
 */
open class GameMapper @Inject constructor() : DataMapper<GameEntity, Game> {
    override fun mapFromEntity(entity: GameEntity): Game =
        Game(
            id = entity.id,
            name = entity.name,
            backgroundImage = entity.backgroundImage,
            rating = entity.rating,
            parentPlatforms = entity.parentPlatforms,
            genres = entity.genres,
            released = entity.released,
            websiteUrl = entity.websiteUrl,
            playtime = entity.playtime,
            platforms = entity.platforms,
            developers = entity.developers,
            description = entity.description,
            isFavorite = entity.isFavorite
        )

    override fun mapToEntity(model: Game): GameEntity =
        GameEntity(
            id = model.id,
            name = model.name,
            backgroundImage = model.backgroundImage,
            rating = model.rating,
            parentPlatforms = model.parentPlatforms,
            genres = model.genres,
            released = model.released,
            websiteUrl = model.websiteUrl,
            playtime = model.playtime,
            platforms = model.platforms,
            developers = model.developers,
            description = model.description,
            isFavorite = model.isFavorite
        )

    override fun mapFromEntities(entities: List<GameEntity>): List<Game> =
        entities.map {
            Game(
                id = it.id,
                name = it.name,
                backgroundImage = it.backgroundImage,
                rating = it.rating,
                parentPlatforms = it.parentPlatforms,
                genres = it.genres,
                released = it.released,
                websiteUrl = it.websiteUrl,
                playtime = it.playtime,
                platforms = it.platforms,
                developers = it.developers,
                description = it.description,
                isFavorite = it.isFavorite
            )
        }
}
