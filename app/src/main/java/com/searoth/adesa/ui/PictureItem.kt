package com.searoth.adesa.ui

import androidx.databinding.ViewDataBinding
import com.searoth.adesa.R
import com.searoth.adesa.databinding.ItemPictureOfTheDayBinding
import com.xwray.groupie.databinding.BindableItem

class PictureItem  constructor(var pictureViewModel: PictureViewModel) : BindableItem<ViewDataBinding>() {
    override fun getLayout(): Int {
        return R.layout.item_picture_of_the_day
    }

    override fun bind(viewBinding: ViewDataBinding, position: Int) {
        (viewBinding as ItemPictureOfTheDayBinding).apply {
            viewModel = pictureViewModel
        }
    }
}