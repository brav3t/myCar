package com.nik.mycar.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.nik.mycar.R
import com.nik.mycar.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.main_activity)
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity)
//        val navController = binding.navHostFragment
    }
}