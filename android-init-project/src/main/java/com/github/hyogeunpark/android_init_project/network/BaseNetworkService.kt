package com.github.hyogeunpark.android_init_project.network

import androidx.viewbinding.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val TIME_OUT = 40L

abstract class BaseNetworkService {
    val defaultClientBuilder by lazy {
        OkHttpClient.Builder()
            .readTimeout(TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
    }
    protected abstract fun baseURL(): String
    protected inline fun <reified T> retrofit(loggingLevel: LoggingLevel = LoggingLevel.BASIC, clientBuilder: OkHttpClient.Builder = defaultClientBuilder, factory: Converter.Factory = GsonConverterFactory.create()): T {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = when (loggingLevel) {
                LoggingLevel.HEADERS -> HttpLoggingInterceptor.Level.HEADERS
                LoggingLevel.BASIC -> HttpLoggingInterceptor.Level.BASIC
                LoggingLevel.BODY -> HttpLoggingInterceptor.Level.BODY
                LoggingLevel.NONE -> HttpLoggingInterceptor.Level.NONE
            }
        }

        clientBuilder.addNetworkInterceptor(httpLoggingInterceptor)

        return Retrofit.Builder()
                .baseUrl(baseURL())
                .addConverterFactory(factory)
                .client(clientBuilder.build())
                .build()
                .create(T::class.java)
    }
}

enum class LoggingLevel {
    HEADERS, BASIC, BODY, NONE
}