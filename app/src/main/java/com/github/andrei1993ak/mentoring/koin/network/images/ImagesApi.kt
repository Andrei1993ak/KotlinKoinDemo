package com.github.andrei1993ak.mentoring.koin.network.images

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ImagesApi {

    @GET("search")
    fun getImages(@Query("limit") limit: Int): Single<List<Image>>
}