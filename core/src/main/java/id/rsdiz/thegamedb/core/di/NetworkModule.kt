package id.rsdiz.thegamedb.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.rsdiz.thegamedb.core.data.source.remote.network.ApiService
import id.rsdiz.thegamedb.core.utils.Const
import id.rsdiz.thegamedb.core.utils.ParameterInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    fun provideOkHttpClient() =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(ParameterInterceptor(Const.RAWG_API_KEY))
            .connectTimeout(Const.TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(Const.TIMEOUT, TimeUnit.SECONDS)
            .build()

    @Provides
    fun provideApiService(client: OkHttpClient): ApiService =
        Retrofit.Builder()
            .baseUrl(Const.RAWG_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build().create(ApiService::class.java)
}
