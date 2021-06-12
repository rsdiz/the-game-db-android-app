package id.rsdiz.thegamedb.mobile.developer.factory

import id.rsdiz.thegamedb.core.domain.model.Game

class GameFactory {
    companion object Factory {
        private fun makeGame(developer: String): Game =
            Game(
                DataFactory.randomInt(),
                DataFactory.randomUuid(),
                DataFactory.randomUuid(),
                DataFactory.randomDouble().toFloat(),
                DataFactory.randomUuid(),
                DataFactory.randomUuid(),
                DataFactory.randomUuid(),
                DataFactory.randomUuid(),
                DataFactory.randomInt(),
                DataFactory.randomUuid(),
                developer,
                DataFactory.randomUuid(),
                DataFactory.randomBoolean()
            )

        fun makeGameList(count: Int): List<Game> {
            val gameList = mutableListOf<Game>()
            val dev1 = DataFactory.randomUuid()
            val dev2 = DataFactory.randomUuid()
            repeat(count) {
                gameList.add(makeGame(if (it % 2 == 0) dev1 else dev2))
            }
            return gameList
        }
    }
}
