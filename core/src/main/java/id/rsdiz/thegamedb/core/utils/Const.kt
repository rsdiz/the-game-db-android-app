package id.rsdiz.thegamedb.core.utils

import id.rsdiz.thegamedb.core.BuildConfig

/**
 * Object containing all fixed value in whole projects
 */
object Const {
    const val DB_NAME = "Games"
    const val PASSPHRASE = "TheGameDB"
    const val TIMEOUT = 120L
    const val RAWG_API_BASE_URL = "https://api.rawg.io/api/"
    const val RAWG_HOSTNAME = "api.rawg.io"
    const val RAWG_API_KEY = BuildConfig.API_KEY
    val RAWG_SSL_PIN = arrayOf(
        BuildConfig.PIN_SSL_1,
        BuildConfig.PIN_SSL_2,
        BuildConfig.PIN_SSL_3,
        BuildConfig.PIN_SSL_4
    )
    const val PARAM_KEY = "key"
    const val PC = "pc"
    const val XBOX = "xbox"
    const val PLAYSTATION = "playstation"
    const val ANDROID = "android"
    const val LINUX = "linux"
    const val IOS = "ios"
    const val MAC = "apple macintosh"
    const val NINTENDO = "nintendo"
}
