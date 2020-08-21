package com.github.hyogeunpark.android_init_project.network

import androidx.viewbinding.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
    protected inline fun <reified T> retrofit(clientBuilder: OkHttpClient.Builder): T {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.HEADERS
        }

        if (BuildConfig.DEBUG) clientBuilder.addNetworkInterceptor(httpLoggingInterceptor)

        return Retrofit.Builder()
                .baseUrl(baseURL())
                .addConverterFactory(GsonConverterFactory.create())
                .client(clientBuilder.build())
                .build()
                .create(T::class.java)
    }
}