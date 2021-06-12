package id.rsdiz.thegamedb.core.factory

import id.rsdiz.thegamedb.core.data.source.local.entity.GameEntity
import id.rsdiz.thegamedb.core.data.source.remote.response.games.*
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

        /**
         * Returns a Game
         *
         */
        fun makeGame(): Game =
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

        /**
         * Returns a GameEntity
         *
         */
        fun makeGameEntity(): GameEntity =
            GameEntity(
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
         * Returns list of GameEntity
         *
         */
        fun makeGameEntities(count: Int): List<GameEntity> {
            val gameEntities = mutableListOf<GameEntity>()
            repeat(count) {
                gameEntities.add(makeGameEntity())
            }
            return gameEntities
        }

        /**
         * Returns a GameResponse
         *
         */
        fun makeGameResponse(): GameResponse =
            GameResponse(
                DataFactory.randomInt(),
                DataFactory.randomUuid(),
                DataFactory.randomUuid(),
                DataFactory.randomDouble().toString(),
                DataFactory.randomUuid(),
                DataFactory.randomUuid(),
                DataFactory.randomDouble().toFloat(),
                DataFactory.randomInt(),
                listOf(
                    ParentPlatformsResponse(PlatformResponse(DataFactory.randomUuid())),
                    ParentPlatformsResponse(PlatformResponse(DataFactory.randomUuid()))
                ),
                listOf(
                    PlatformsResponse(PlatformResponse(DataFactory.randomUuid())),
                    PlatformsResponse(PlatformResponse(DataFactory.randomUuid()))
                ),
                listOf(
                    DevelopersResponse(DataFactory.randomUuid(), DataFactory.randomUuid()),
                    DevelopersResponse(DataFactory.randomUuid(), DataFactory.randomUuid())
                ),
                listOf(
                    GenreResponse(
                        DataFactory.randomInt(),
                        DataFactory.randomUuid(),
                        DataFactory.randomUuid()
                    ),
                    GenreResponse(
                        DataFactory.randomInt(),
                        DataFactory.randomUuid(),
                        DataFactory.randomUuid()
                    )
                )
            )

        /**
         * Returns list of GameResponse
         *
         */
        fun makeGameResponseList(count: Int): List<GameResponse> {
            val gameResponseList = mutableListOf<GameResponse>()
            repeat(count) {
                gameResponseList.add(makeGameResponse())
            }
            return gameResponseList
        }
    }
}
