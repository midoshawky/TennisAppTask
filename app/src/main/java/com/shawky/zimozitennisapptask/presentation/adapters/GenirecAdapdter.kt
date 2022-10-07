package com.shawky.zimozitennisapptask.presentation.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import kotlin.collections.ArrayList

class GenericRecyclerAdapter<T :Any?> @JvmOverloads constructor(
    private val dataSet: ArrayList<T>, @LayoutRes val layoutID: Int,
    private val bindingInterface: GenericSimpleRecyclerBindingInterface<T> ?= null,
    private val onItemPressedCallBack: OnPressedInterface<T> ?= null,
) : RecyclerView.Adapter<GenericRecyclerAdapter<T>.ViewHolder>() {
    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutID, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataSet[position]

        bindingInterface?.bindData(item,holder.view,position)
        holder.view.setOnClickListener {
            onItemPressedCallBack?.onPressed(item)
            onItemPressedCallBack?.onPressedWithPos(item,position)
        }
    }

    override fun getItemCount(): Int = dataSet.size


    fun <B : T>updateDateGeneric(data: ArrayList<B>){
        dataSet.clear()
        dataSet.addAll(data)
        notifyDataSetChanged()
    }

    fun updateData(data: List<T>){
        dataSet.clear()
        dataSet.addAll(data)
        notifyDataSetChanged()
    }

    fun deleteItem(index:Int){
        Log.i("deleteItem","$index ${dataSet.size}")
        dataSet.removeAt(index)
        notifyItemRemoved(index)
        Log.i("deleteItem","$index ${dataSet.size}")
    }


}


interface GenericSimpleRecyclerBindingInterface<T:Any?> {
    fun bindData(item: T,view:View,position: Int ?= 0){}
}


interface OnPressedInterface<T:Any?> {
    fun onPressed(item: Any?){}
    fun onPressedWithPos(item: Any?,pos:Int){}
}
