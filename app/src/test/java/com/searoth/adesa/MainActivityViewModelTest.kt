package com.searoth.adesa

import android.app.Application
import com.nhaarman.mockito_kotlin.mock
import com.searoth.adesa.common.ApiService
import com.searoth.adesa.di.SeaRothServiceLocator
import com.searoth.adesa.repo.PictureRepository
import com.searoth.adesa.ui.MainActivityViewModel
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldEqual
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivityViewModelTest {

    val app: AdesaApplication = mock()
    val mockApplication = Mockito.mock(Application::class.java)
    val repo = SeaRothServiceLocator.put(PictureRepository::class.java, PictureRepository())

    @Before
    fun Before(){
        Mockito.`when`(mockApplication.getString(Mockito.anyInt())).thenReturn("")
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.nasa.gov/planetary/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(ApiService::class.java)
        SeaRothServiceLocator.put(ApiService::class.java, service)
        SeaRothServiceLocator.put(Application::class.java, mockApplication)
    }

    @Test
    fun initial_load_should_populate_image() {
        val mainActivityViewModel = MainActivityViewModel(app)
        mainActivityViewModel.numberOfItems.get() shouldEqual "1"
    }
}