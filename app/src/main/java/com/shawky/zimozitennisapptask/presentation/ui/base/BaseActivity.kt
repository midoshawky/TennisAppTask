package com.shawky.zimozitennisapptask.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseBindingActivity<B : ViewBinding> : AppCompatActivity() {
    lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
    }

    abstract fun getViewBinding(): B

}