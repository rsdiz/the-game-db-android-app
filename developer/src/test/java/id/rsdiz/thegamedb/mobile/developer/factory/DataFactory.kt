package id.rsdiz.thegamedb.mobile.developer.factory

import java.util.*

/**
 * This class is used to generate data objects for usage on tests
 */
class DataFactory {

    companion object Factory {

        /**
         * Returns a random String object
         *
         */
        fun randomUuid(): String {
            return UUID.randomUUID().toString()
        }

        /**
         * Returns a random Int object
         */
        fun randomInt(): Int {
            return Random().nextInt()
        }

        /**
         * Returns a random Double object
         */
        fun randomDouble(): Double {
            return Random().nextDouble()
        }

        /**
         * Returns a random boolean
         */
        fun randomBoolean(): Boolean {
            return Math.random() < 0.5
        }
    }
}
