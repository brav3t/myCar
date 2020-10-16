package com.nik.mycar.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.nik.mycar.databinding.FragmentCarDetailsBinding
import com.nik.mycar.viewmodels.CarDetailsVM
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CarDetailsFragment : Fragment() {

    private val carDetailVM: CarDetailsVM by viewModels()
    private val args: CarDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCarDetailsBinding.inflate(inflater, container, false)
        binding.carDetailsVM = carDetailVM.apply {
            carId = args.carId
        }

        binding.btnDeleteCar.setOnClickListener{
            carDetailVM.deleteCar()
            val directions = CarDetailsFragmentDirections.actionCarDetailsFragmentToCarListFragment()
            it.findNavController().navigate(directions)
        }

        return binding.root
    }
}
