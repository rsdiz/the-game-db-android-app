package id.rsdiz.thegamedb.mobile.favorite.factory

import id.rsdiz.thegamedb.core.domain.model.Game

/**
 * This class is used to generate game objects for usage on tests
 *
 */
class GameFactory {

    /**
     * Factory object
     *
     */
    companion object Factory {
        private fun makeGame(): Game =
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
                DataFactory.randomUuid(),
                DataFactory.randomUuid(),
                DataFactory.randomBoolean()
            )

        /**
         * Returns a list of Game
         *
         */
        fun makeGameList(count: Int): List<Game> {
            val gameList = mutableListOf<Game>()
            repeat(count) {
                gameList.add(makeGame())
            }
            return gameList
        }
    }
}
