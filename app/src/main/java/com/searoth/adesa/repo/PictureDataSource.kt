package com.searoth.adesa.repo

import com.searoth.adesa.model.PictureOfTheDay
import io.reactivex.Observable

interface PictureDataSource {
    fun getPictureFromNasa(apiKey: String, date: String) : Observable<PictureOfTheDay>
}