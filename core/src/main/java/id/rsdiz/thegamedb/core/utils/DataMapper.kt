package id.rsdiz.thegamedb.core.utils

import id.rsdiz.thegamedb.core.data.source.local.entity.GameEntity
import id.rsdiz.thegamedb.core.data.source.remote.response.*
import id.rsdiz.thegamedb.core.domain.model.Game

object DataMapper {
    fun mapResponsesToEntities(listResponses: List<GameResponse>): List<GameEntity> {
        val list = mutableListOf<GameEntity>()

        listResponses.map {
            val parentPlatforms = getParentPlatformsName(it.parentPlatforms)
            val genres = getGenresName(it.genres)
            val platforms = getPlatformsName(it.platforms.platform)
            val developers = getDevelopersName(it.developers)

            val game = GameEntity(
                id = it.id,
                name = it.name,
                backgroundImage = it.backgroundImage,
                rating = it.rating,
                parentPlatforms = parentPlatforms,
                genres = genres,
                released = it.released,
                websiteUrl = it.websiteUrl ?: "",
                playtime = it.playtime,
                platforms = platforms,
                developers = developers,
                description = it.description ?: "",
                isFavorite = false
            )
            list.add(game)
        }

        return list
    }

    fun mapEntitiesToDomain(listEntity: List<GameEntity>): List<Game> =
        listEntity.map {
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

    fun mapDomainToEntity(domain: Game): GameEntity =
        GameEntity(
            id = domain.id,
            name = domain.name,
            backgroundImage = domain.backgroundImage,
            rating = domain.rating,
            parentPlatforms = domain.parentPlatforms,
            genres = domain.genres,
            released = domain.released,
            websiteUrl = domain.websiteUrl,
            playtime = domain.playtime,
            platforms = domain.platforms,
            developers = domain.developers,
            description = domain.description,
            isFavorite = domain.isFavorite
        )

    fun mapEntityToDomain(entity: GameEntity): Game =
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

    fun mapResponseToEntity(response: GameResponse): GameEntity {
        val parentPlatforms = getParentPlatformsName(response.parentPlatforms)
        val genres = getGenresName(response.genres)
        val platforms = getPlatformsName(response.platforms.platform)
        val developers = getDevelopersName(response.developers)

        return GameEntity(
            id = response.id,
            name = response.name,
            backgroundImage = response.backgroundImage,
            rating = response.rating,
            parentPlatforms = parentPlatforms,
            genres = genres,
            released = response.released,
            websiteUrl = response.websiteUrl ?: "",
            playtime = response.playtime,
            platforms = platforms,
            developers = developers,
            description = response.description ?: "",
            isFavorite = false
        )
    }

    private fun getParentPlatformsName(data: ParentPlatformsResponse?): String {
        val result = StringBuilder().append("")

        data?.let {
            for (i in it.platform.indices)
                if (i < it.platform.size - 1) result.append("${it.platform[i].name}, ")
                else result.append(it.platform[i].name)
        }

        return result.toString()
    }

    private fun getGenresName(data: List<GenreResponse>?): String {
        val result = StringBuilder().append("")

        data?.let {
            for (i in it.indices)
                if (i < it.size - 1) result.append("${it[i].name}, ")
                else result.append(it[i].name)
        }

        return result.toString()
    }

    private fun getPlatformsName(data: List<PlatformResponse>?): String {
        val result = StringBuilder().append("")

        data?.let {
            for (i in it.indices)
                if (i < it.size - 1) result.append("${it[i].name}, ")
                else result.append(it[i].name)
        }

        return result.toString()
    }

    private fun getDevelopersName(data: List<DevelopersResponse>?): String {
        val result = StringBuilder().append("")

        data?.let {
            for (i in it.indices)
                if (i < it.size - 1) result.append("${it[i].name}, ")
                else result.append(it[i].name)
        }

        return result.toString()
    }
}
