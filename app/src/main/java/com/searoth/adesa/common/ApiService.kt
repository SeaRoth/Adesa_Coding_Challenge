package com.searoth.adesa.common

import com.searoth.adesa.BuildConfig
import com.searoth.adesa.model.PictureOfTheDay
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    companion object {
        const val API_KEY = BuildConfig.NASA_API_KEY
    }

    @GET("apod")
    fun getPictureOfTheDay(@Query("api_key") apiKey: String, @Query("date") date: String) : Observable<PictureOfTheDay>
}