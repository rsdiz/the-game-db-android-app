package id.rsdiz.thegamedb.core.utils

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Class to invoke the Api Key
 */
class ParameterInterceptor(private val apiKey: String) : Interceptor {
    /**
     * Inserting Api Key to URL
     */
    override fun intercept(chain: Interceptor.Chain): Response {
        var original = chain.request()
        val url = original.url.newBuilder().addQueryParameter(Const.PARAM_KEY, apiKey).build()
        original = original.newBuilder().url(url).build()
        return chain.proceed(original)
    }
}
