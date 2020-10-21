package com.nik.mycar.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.nik.mycar.adapters.FuellingListAdapter
import com.nik.mycar.data.AppDatabase
import com.nik.mycar.databinding.FragmentFuellingListBinding
import com.nik.mycar.viewmodels.CarDetailsViewModel
import com.nik.mycar.viewmodels.CarDetailsViewModelFactory

class FuellingListFragment : Fragment() {

    private lateinit var carDetailsViewModel: CarDetailsViewModel
    private lateinit var factory: CarDetailsViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(this.activity).application
        val carDao = AppDatabase.getInstance(application).carDao()
        val fuellingDao = AppDatabase.getInstance(application).fuellingDao()
        val args = CarDetailsFragmentArgs.fromBundle(requireArguments())
        factory = CarDetailsViewModelFactory(carDao, fuellingDao, args.carId)
        carDetailsViewModel = ViewModelProvider(this, factory).get(CarDetailsViewModel::class.java)

        val binding = FragmentFuellingListBinding.inflate(inflater, container, false)

        val adapter = FuellingListAdapter()
        binding.fuellingList.adapter = adapter
        carDetailsViewModel.fuellings.observe(viewLifecycleOwner) { fuellingList ->
            adapter.submitList(fuellingList)
        }

        return binding.root
    }
}
