package id.rsdiz.thegamedb.core.factory

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
    }
}
