package com.searoth.adesa.ui

import androidx.databinding.ObservableField
import androidx.databinding.ViewDataBinding
import com.searoth.adesa.common.BaseBindableViewModel
import com.searoth.adesa.model.PictureOfTheDay
import com.xwray.groupie.databinding.BindableItem

class PictureViewModel(val pictureOfTheDay: PictureOfTheDay, val mainActivityViewModel: MainActivityViewModel) : BaseBindableViewModel() {

    override fun getItemFactory(): (BaseBindableViewModel) -> BindableItem<ViewDataBinding> {
        return { it -> PictureItem((it as PictureViewModel))}
    }

    val title        = ObservableField<String>(pictureOfTheDay.title)
    val imgUrl      = ObservableField<String?>(pictureOfTheDay.url)

    fun showDescription() {
        mainActivityViewModel.actionTalkToActivity.actionOccuredPost(pictureOfTheDay.explanation)
    }
}