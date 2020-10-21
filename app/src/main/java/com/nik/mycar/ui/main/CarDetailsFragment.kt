package com.nik.mycar.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.*
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.nik.mycar.data.AppDatabase
import com.nik.mycar.databinding.FragmentCarDetailsBinding
import com.nik.mycar.viewmodels.CarDetailsViewModel
import com.nik.mycar.viewmodels.CarDetailsViewModelFactory
import kotlinx.android.synthetic.main.fragment_car_details.*

class CarDetailsFragment : Fragment() {

    private lateinit var carDetailsViewModel: CarDetailsViewModel
    private lateinit var factory: CarDetailsViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(this.activity).application
        val carDao = AppDatabase.getInstance(application).carDao()
        val fuellingDao = AppDatabase.getInstance(application).fuellingDao()
        val args = CarDetailsFragmentArgs.fromBundle(requireArguments())
        factory = CarDetailsViewModelFactory(carDao, fuellingDao, args.carId)
        carDetailsViewModel = ViewModelProvider(this, factory).get(CarDetailsViewModel::class.java)

        val binding = FragmentCarDetailsBinding.inflate(inflater, container, false).apply {
            viewModel = carDetailsViewModel
            lifecycleOwner = this@CarDetailsFragment
        }

        binding.btnDeleteCar.setOnClickListener{
            val directions = CarDetailsFragmentDirections.actionCarDetailsFragmentToCarListFragment()
            it.findNavController().navigate(directions)
            carDetailsViewModel.deleteCar()
        }

        binding.btnAddFuelling.setOnClickListener{
            val directions = CarDetailsFragmentDirections.actionCarDetailsFragmentToAddFuellingDialogFragment(args.carId)
            it.findNavController().navigate(directions)
        }

        binding.btnFuellingCost.setOnClickListener{
            val direction = CarDetailsFragmentDirections.actionCarDetailsFragmentToFuellingListFragment(args.carId)
            it.findNavController().navigate(direction)
        }

        binding.btnServiceCost.setOnClickListener {
            Toast.makeText(context, "Not implemented", Toast.LENGTH_SHORT).show()
        }

        binding.btnAddService.setOnClickListener{
            Toast.makeText(context, "Not implemented", Toast.LENGTH_SHORT).show()
        }

        binding.btnMileage.setOnClickListener {
            Toast.makeText(context, "Not implemented", Toast.LENGTH_SHORT).show()
        }

        binding.btnAddCheckpoint.setOnClickListener{
            Toast.makeText(context, "Not implemented", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }
}
