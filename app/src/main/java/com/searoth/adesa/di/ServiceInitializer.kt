package com.searoth.adesa.di

import android.app.Application
import android.content.Context
import com.searoth.adesa.common.ApiService
import com.searoth.adesa.repo.PictureRepository
import com.squareup.picasso.Picasso
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ServiceInitializer {
    companion object {
        fun initServices(application: Application) {
            initApplication(application)
            initNasaApi(application)
            initRepo()
        }

        private fun initNasaApi(application: Context) {
            //https://api.nasa.gov/planetary/apod?api_key=Ch6vdpsXxzWBtfMKUZ3OC48cFYwYPnfIP4TmSeSA&date=2019-02-13
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.nasa.gov/planetary/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            SeaRothServiceLocator.put(Retrofit::class.java, retrofit)

            application.let {
                val picasso = Picasso.Builder(it).build()
                SeaRothServiceLocator.put(Picasso::class.java, picasso)
            }

            val service = retrofit.create(ApiService::class.java)
            SeaRothServiceLocator.put(ApiService::class.java, service)
        }

        private fun initRepo() {
            SeaRothServiceLocator.put(PictureRepository::class.java, PictureRepository.getInstance())
        }

        private fun initApplication(application: Application) {
            SeaRothServiceLocator.put(Application::class.java, application)
        }
    }
}
