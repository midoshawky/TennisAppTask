package com.shawky.zimozitennisapptask.presentation.adapters

import android.graphics.Color
import android.view.View
import androidx.databinding.DataBindingUtil
import com.shawky.zimozitennisapptask.databinding.TennisPlayerItemLayoutBinding
import com.shawky.zimozitennisapptask.domain.models.TennisPlayer

object RecyclerViewAdaptersBinding {

    fun classesAdapterBinding() = object : GenericSimpleRecyclerBindingInterface<TennisPlayer>{
        override fun bindData(item: TennisPlayer, view: View, position: Int?) {
           DataBindingUtil.bind<TennisPlayerItemLayoutBinding>(view)?.apply {
               player = item
           }
        }
    }
}