package com.shawky.zimozitennisapptask.presentation.helpers

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shawky.zimozitennisapptask.R
import com.shawky.zimozitennisapptask.data.di.GlideApp
import com.shawky.zimozitennisapptask.domain.models.ResultState
import com.shawky.zimozitennisapptask.presentation.adapters.GenericRecyclerAdapter

@BindingAdapter("url")
fun ImageView.loadImageFromUrl(url : String?) {
        GlideApp.with(context)
            .load(url)
            .placeholder(R.drawable.player_placeholder)
            .error(R.drawable.player_placeholder)
            .into(this)
}
@BindingAdapter("visible")
fun View.isVisible(visible: Boolean){
    visibility = if(visible) View.VISIBLE else View.GONE
}

@BindingAdapter(value = ["recyclerAdapter","isLinear","cols","isHorizontal"], requireAll = false)
fun <T : RecyclerView.Adapter<*>> RecyclerView.setup(customAdapter:T, isLinear:Boolean = true, cols : Int? = 2, isHorizontal : Boolean = false){
    val orientation = if(isHorizontal) RecyclerView.HORIZONTAL else RecyclerView.VERTICAL
    adapter = customAdapter
    layoutManager =   if(isLinear) LinearLayoutManager(context,orientation,false) else GridLayoutManager(context,cols?:2)
}
@BindingAdapter("state")
fun <T> RecyclerView.withState(state : ResultState<T>?){
    if(state is ResultState.Success && adapter is GenericRecyclerAdapter<*>) {
        (adapter as GenericRecyclerAdapter<*>).updateData(state.data as List<Nothing>)
    }
}