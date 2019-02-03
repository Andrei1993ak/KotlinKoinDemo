package com.github.andrei1993ak.mentoring.koin.di

import com.github.andrei1993ak.mentoring.koin.di.DatasourceProperties.IMAGES_SERVER_URL
import com.github.andrei1993ak.mentoring.koin.di.DatasourceProperties.RANDOM_TEXT_SERVER_URL
import com.github.andrei1993ak.mentoring.koin.network.images.ImagesApi
import com.github.andrei1993ak.mentoring.koin.network.randomText.RandomTextApi
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val remoteDatasourceModule = module {
    single { createOkHttpClient() }
    single { createWebService<ImagesApi>(get(), IMAGES_SERVER_URL) }
    single { createWebService<RandomTextApi>(get(), RANDOM_TEXT_SERVER_URL) }
}

object DatasourceProperties {
    const val IMAGES_SERVER_URL = "https://api.thecatapi.com/v1/images/"
    const val RANDOM_TEXT_SERVER_URL = "http://www.randomtext.me/api/"
}

fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
    return OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor).build()
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())).build()
    return retrofit.create(T::class.java)
}
