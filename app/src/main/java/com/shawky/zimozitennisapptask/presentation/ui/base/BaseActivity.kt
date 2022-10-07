package com.shawky.zimozitennisapptask.presentation.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseBindingActivity<B : ViewBinding> : AppCompatActivity() {
    private var _binding: B? = null
    val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = getViewBinding()
        setContentView(binding.root)
    }

    abstract fun getViewBinding(): B

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}