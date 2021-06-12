package id.rsdiz.thegamedb.mobile.developer.factory

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
        private fun makeDevelopers(): Developers =
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
    }
}
