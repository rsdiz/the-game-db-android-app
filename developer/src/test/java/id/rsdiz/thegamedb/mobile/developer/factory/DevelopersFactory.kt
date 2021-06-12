package id.rsdiz.thegamedb.mobile.developer.factory

import id.rsdiz.thegamedb.core.data.source.local.entity.DevelopersEntity
import id.rsdiz.thegamedb.core.data.source.remote.response.developer.DeveloperResponse
import id.rsdiz.thegamedb.core.domain.model.Developers

class DevelopersFactory {
    companion object Factory {
        fun makeDevelopers(): Developers =
            Developers(
                DataFactory.randomInt(),
                DataFactory.randomUuid(),
                DataFactory.randomUuid(),
                DataFactory.randomInt(),
                DataFactory.randomUuid(),
                DataFactory.randomUuid()
            )

        fun makeDevelopersList(count: Int): List<Developers> {
            val developersList = mutableListOf<Developers>()
            repeat(count) {
                developersList.add(makeDevelopers())
            }
            return developersList
        }

        fun makeDevelopersEntity(): DevelopersEntity =
            DevelopersEntity(
                DataFactory.randomInt(),
                DataFactory.randomUuid(),
                DataFactory.randomUuid(),
                DataFactory.randomInt(),
                DataFactory.randomUuid(),
                DataFactory.randomUuid()
            )

        fun makeDevelopersEntities(count: Int): List<DevelopersEntity> {
            val developersEntities = mutableListOf<DevelopersEntity>()
            repeat(count) {
                developersEntities.add(makeDevelopersEntity())
            }
            return developersEntities
        }

        fun makeDevelopersResponse(): DeveloperResponse =
            DeveloperResponse(
                DataFactory.randomInt(),
                DataFactory.randomUuid(),
                DataFactory.randomUuid(),
                DataFactory.randomInt(),
                DataFactory.randomUuid(),
                DataFactory.randomUuid()
            )

        fun makeDevelopersResponseList(count: Int): List<DeveloperResponse> {
            val developerResponseList = mutableListOf<DeveloperResponse>()
            repeat(count) {
                developerResponseList.add(makeDevelopersResponse())
            }
            return developerResponseList
        }
    }
}
