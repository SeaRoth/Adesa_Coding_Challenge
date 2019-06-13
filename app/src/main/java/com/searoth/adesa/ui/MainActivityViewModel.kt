package com.searoth.adesa.ui

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import com.searoth.adesa.common.ApiService.Companion.API_KEY
import com.searoth.adesa.common.LiveDataActionWithData
import com.searoth.adesa.di.SeaRothServiceLocator
import com.searoth.adesa.model.PictureOfTheDay
import com.searoth.adesa.repo.PictureRepository
import com.xwray.groupie.Section
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit

class MainActivityViewModel(app: Application) : AndroidViewModel(app) {
    val FILTER_DATE_PATTERN = "yyyy-MM-dd"
    //repo
    private val pictureRepository    = SeaRothServiceLocator.resolve(PictureRepository::class.java)

    //groupie
    val mainSection = Section()

    //actions
    val actionTalkToActivity = LiveDataActionWithData<String>()

    //disposables
    lateinit var disposableObserver: DisposableObserver<PictureOfTheDay>

    //observable
    val numberOfItems = ObservableField<String>()

    //data

    init {
        getPictureOfTheDay("2019-02-13")
    }

    fun getPictureOfTheDay(date: String) {
        disposableObserver = object : DisposableObserver<PictureOfTheDay>() {
            override fun onComplete() {}

            override fun onNext(t: PictureOfTheDay) {
                mainSection.add(PictureItem(PictureViewModel(t, this@MainActivityViewModel)))
            }
            override fun onError(e: Throwable) {
                actionTalkToActivity.actionOccuredPost("Error getting ")
            }
        }
        pictureRepository.getPictureFromNasa(API_KEY,date)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .debounce(400, TimeUnit.MILLISECONDS)
            .subscribe(disposableObserver)
    }

}