package id.rsdiz.thegamedb.core.factory

import id.rsdiz.thegamedb.core.data.source.local.entity.DevelopersEntity
import id.rsdiz.thegamedb.core.data.source.remote.response.developer.DeveloperResponse
import id.rsdiz.thegamedb.core.domain.model.Developers

/**
 * This class is used to generate developers objects for usage on tests
 *
 */
class DevelopersFactory {

    /**
     * Factory object
     *
     */
    companion object Factory {

        /**
         * Returns a Developers
         *
         */
        fun makeDevelopers(): Developers =
            Developers(
                DataFactory.randomInt(),
                DataFactory.randomUuid(),
                DataFactory.randomUuid(),
                DataFactory.randomInt(),
                DataFactory.randomUuid(),
                DataFactory.randomUuid()
            )

        /**
         * Returns list of Developers
         *
         */
        fun makeDevelopersList(count: Int): List<Developers> {
            val developersList = mutableListOf<Developers>()
            repeat(count) {
                developersList.add(makeDevelopers())
            }
            return developersList
        }

        /**
         * Returns a DevelopersEntity
         *
         */
        fun makeDevelopersEntity(): DevelopersEntity =
            DevelopersEntity(
                DataFactory.randomInt(),
                DataFactory.randomUuid(),
                DataFactory.randomUuid(),
                DataFactory.randomInt(),
                DataFactory.randomUuid(),
                DataFactory.randomUuid()
            )

        /**
         * Returns list of DevelopersEntity
         *
         */
        fun makeDevelopersEntities(count: Int): List<DevelopersEntity> {
            val developersEntities = mutableListOf<DevelopersEntity>()
            repeat(count) {
                developersEntities.add(makeDevelopersEntity())
            }
            return developersEntities
        }

        /**
         * Returns a DeveloperResponse
         *
         */
        fun makeDevelopersResponse(): DeveloperResponse =
            DeveloperResponse(
                DataFactory.randomInt(),
                DataFactory.randomUuid(),
                DataFactory.randomUuid(),
                DataFactory.randomInt(),
                DataFactory.randomUuid(),
                DataFactory.randomUuid()
            )

        /**
         * Returns list of DeveloperResponse
         *
         */
        fun makeDevelopersResponseList(count: Int): List<DeveloperResponse> {
            val developerResponseList = mutableListOf<DeveloperResponse>()
            repeat(count) {
                developerResponseList.add(makeDevelopersResponse())
            }
            return developerResponseList
        }
    }
}
