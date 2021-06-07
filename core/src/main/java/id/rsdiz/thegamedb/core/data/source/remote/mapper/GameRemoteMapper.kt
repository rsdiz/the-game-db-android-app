package id.rsdiz.thegamedb.core.data.source.remote.mapper

import id.rsdiz.thegamedb.core.data.source.local.entity.GameEntity
import id.rsdiz.thegamedb.core.data.source.remote.mapper.base.EntityMapper
import id.rsdiz.thegamedb.core.data.source.remote.response.games.*
import javax.inject.Inject

/**
 * Maps a Game from remote (API) to our model
 */
open class GameRemoteMapper @Inject constructor() : EntityMapper<GameResponse, GameEntity> {
    override fun mapRemoteToEntity(remote: GameResponse): GameEntity {
        val parentPlatforms = getParentPlatformsName(remote.parentPlatforms)
        val genres = getGenresName(remote.genres)
        val platforms = getPlatformsName(remote.platforms)
        val developers = getDevelopersName(remote.developers)

        return GameEntity(
            id = remote.id,
            name = remote.name,
            backgroundImage = remote.backgroundImage,
            rating = remote.rating,
            parentPlatforms = parentPlatforms,
            genres = genres,
            released = remote.released,
            websiteUrl = remote.websiteUrl ?: "",
            playtime = remote.playtime,
            platforms = platforms,
            developers = developers,
            description = remote.description ?: "",
            isFavorite = false
        )
    }

    override fun mapRemoteToEntities(remotes: List<GameResponse>): List<GameEntity> {
        val list = mutableListOf<GameEntity>()

        remotes.map {
            val parentPlatforms = getParentPlatformsName(it.parentPlatforms)
            val genres = getGenresName(it.genres)
            val platforms = getPlatformsName(it.platforms)
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

    private fun getParentPlatformsName(data: List<ParentPlatformsResponse>?): String {
        val result = StringBuilder().append("")

        data?.let {
            for (i in it.indices)
                if (i < it.size - 1) result.append("${it[i].platform.name}, ")
                else result.append(it[i].platform.name)
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

    private fun getPlatformsName(data: List<PlatformsResponse>?): String {
        val result = StringBuilder().append("")

        data?.let {
            for (i in it.indices)
                if (i < it.size - 1) result.append("${it[i].platform.name}, ")
                else result.append(it[i].platform.name)
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
