package com.jakub.zajac.common.network.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideBaseUrl(): String {
        return "https://dataservice.accuweather.com/"
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
    ) = OkHttpClient.Builder().readTimeout(30, TimeUnit.SECONDS)
        .connectTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS).addInterceptor(
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        ).addInterceptor{apiParametersAsQuery(it)}.build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, baseUrl: String): Retrofit
    {
        val networkJson = Json { ignoreUnknownKeys = true }
        return Retrofit.Builder()
            .addConverterFactory(networkJson.asConverterFactory("application/json".toMediaType()))
            .baseUrl(baseUrl)
            .client(okHttpClient).build()
    }

    private fun apiParametersAsQuery(chain: Interceptor.Chain) = chain.proceed(
        chain.request().newBuilder().url(
                chain.request().url.newBuilder()
                    .addQueryParameter("apikey", "tpjQAGN86qTGKh4wna8E39bBoMkDMgTi")
                    .addQueryParameter("language", "pl-PL")
                    .build()
            ).build()
    )
}