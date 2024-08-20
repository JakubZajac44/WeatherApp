package com.jakub.zajac.common.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
        ).addInterceptor{apiKeyAsQuery(it)}.build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, baseUrl: String): Retrofit =
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(baseUrl)
            .client(okHttpClient).build()



    private fun apiKeyAsQuery(chain: Interceptor.Chain) = chain.proceed(
        chain.request().newBuilder().url(
                chain.request().url.newBuilder()
                    .addQueryParameter("apikey", "KpV3WbOj72MXQ5h3j4oYY2Lkxuf4yUfV")
                    .addQueryParameter("language", "pl-PL")
                    .build()
            ).build()
    )
}