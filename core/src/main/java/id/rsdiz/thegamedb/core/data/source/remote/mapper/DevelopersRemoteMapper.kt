package id.rsdiz.thegamedb.core.data.source.remote.mapper

import id.rsdiz.thegamedb.core.data.source.local.entity.DevelopersEntity
import id.rsdiz.thegamedb.core.data.source.remote.mapper.base.EntityMapper
import id.rsdiz.thegamedb.core.data.source.remote.response.developer.DeveloperResponse
import javax.inject.Inject

/**
 * Maps a Developers from remote (API) to our model
 */
open class DevelopersRemoteMapper @Inject constructor() :
    EntityMapper<DeveloperResponse, DevelopersEntity> {
    override fun mapRemoteToEntity(remote: DeveloperResponse): DevelopersEntity =
        DevelopersEntity(
            id = remote.id,
            name = remote.name,
            slug = remote.slug,
            gamesCount = remote.gamesCount,
            imageBackground = remote.imageBackground,
            description = remote.description
        )

    override fun mapRemoteToEntities(remotes: List<DeveloperResponse>): List<DevelopersEntity> =
        remotes.map {
            DevelopersEntity(
                id = it.id,
                name = it.name,
                slug = it.slug,
                gamesCount = it.gamesCount,
                imageBackground = it.imageBackground,
                description = it.description
            )
        }
}
