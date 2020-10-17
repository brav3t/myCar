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
import com.nik.mycar.viewmodels.FuellingVM
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CarDetailsFragment : Fragment() {

    private val args: CarDetailsFragmentArgs by navArgs()

    @Inject
    lateinit var fuellingVMFactory: FuellingVM.AssistedFactory

    private val fuellingVM: FuellingVM by viewModels {
        FuellingVM.provideFactory(
            fuellingVMFactory,
            args.carId
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCarDetailsBinding.inflate(inflater, container, false)
        binding.fuellingVM = fuellingVM




        binding.btnDeleteCar.setOnClickListener{
            val directions = CarDetailsFragmentDirections.actionCarDetailsFragmentToCarListFragment(true)
            it.findNavController().navigate(directions)
        }

        return binding.root
    }
}
