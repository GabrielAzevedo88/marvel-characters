package com.android.marvelapi.repository

import com.android.marvelapi.util.md5
import java.util.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MarvelAPI {

    companion object {
        private const val BASE_URL = "http://gateway.marvel.com/v1/public/"

        private const val PUBLIC_KEY = "58a48f0b723df3e9aa6f4069b46619f1"
        private const val PRIVATE_KEY = "f57798e283c307dd79f8d5cbc547bcf8f3948db2"

        private const val PARAM_API_KEY = "apikey"
        private const val PARAM_TIME_STAMP = "ts"
        private const val PARAM_HASH = "hash"

        private const val TIME_ZONE = "UTC"
    }

    private fun getTimeStamp(): String =
        (Calendar.getInstance(TimeZone.getTimeZone(TIME_ZONE)).timeInMillis / 1000L).toString()

    private fun getHttpClient(): OkHttpClient.Builder =
        OkHttpClient.Builder().addInterceptor { chain ->
            val request = chain.request()
            val httpUrl = request.url()
            val timeStamp = getTimeStamp()

            val newHttpUrl = httpUrl
                .newBuilder()
                .addQueryParameter(PARAM_API_KEY, PUBLIC_KEY)
                .addQueryParameter(PARAM_TIME_STAMP, timeStamp)
                .addQueryParameter(PARAM_HASH, "$timeStamp$PRIVATE_KEY$PUBLIC_KEY".md5())
                .build()

            chain.proceed(
                request
                    .newBuilder()
                    .url(newHttpUrl)
                    .build()
            )
        }

    fun getMarvelApiService(): MarvelService = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .client(getHttpClient().build())
        .build()
        .create(MarvelService::class.java)
}
