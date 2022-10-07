package com.shawky.zimozitennisapptask.presentation.helpers

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shawky.zimozitennisapptask.R
import com.shawky.zimozitennisapptask.data.di.GlideApp

@BindingAdapter("url")
fun ImageView.loadImageFromUrl(url : String?) {
        GlideApp.with(context)
            .load(url)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(this)
}

@BindingAdapter(value = ["recyclerAdapter","isLinear","cols","isHorizontal"], requireAll = false)
fun <T : RecyclerView.Adapter<*>> RecyclerView.setup(customAdapter:T, isLinear:Boolean = true, cols : Int? = 2, isHorizontal : Boolean = false){
    val orientation = if(isHorizontal) RecyclerView.HORIZONTAL else RecyclerView.VERTICAL
    adapter = customAdapter
    layoutManager =   if(isLinear) LinearLayoutManager(context,orientation,false) else GridLayoutManager(context,cols?:2)
}