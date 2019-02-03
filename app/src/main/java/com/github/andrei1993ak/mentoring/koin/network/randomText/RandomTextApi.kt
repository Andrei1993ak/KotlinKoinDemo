package com.github.andrei1993ak.mentoring.koin.network.randomText

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface RandomTextApi {

    @GET("lorem/ul-{limit}")
    fun getRandomText(@Path("limit") limit: Int): Single<RandomTextResponse>
}