package com.searoth.adesa.repo

import com.searoth.adesa.common.ApiService
import com.searoth.adesa.di.SeaRothServiceLocator
import com.searoth.adesa.model.PictureOfTheDay
import io.reactivex.Observable

class PictureRepository : PictureDataSource {

    private var apiService : ApiService = SeaRothServiceLocator.resolve(ApiService::class.java)

    override fun getPictureFromNasa(apiKey: String, date: String): Observable<PictureOfTheDay> {
        return apiService.getPictureOfTheDay(apiKey, date)
    }

    companion object {
        private var INSTANCE: PictureRepository? = null

        @JvmStatic fun getInstance() : PictureRepository {
            return INSTANCE ?: PictureRepository().apply { INSTANCE = this }
        }

        @JvmStatic fun destroyInstance(){
            INSTANCE = null
        }
    }
}