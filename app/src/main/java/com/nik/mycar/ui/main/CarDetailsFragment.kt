package com.nik.mycar.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.nik.mycar.R
import com.nik.mycar.databinding.FragmentCarDetailsBinding
import com.nik.mycar.databinding.FragmentCarListBinding
import com.nik.mycar.viewmodels.CarDetailsVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CarDetailsFragment : Fragment() {

    private val carDetailViewModel: CarDetailsVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCarDetailsBinding.inflate(inflater, container, false)



        return binding.root
    }
}
