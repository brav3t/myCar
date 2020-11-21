package com.nik.mycar.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.map
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
        val checkpointDao = AppDatabase.getInstance(application).checkpointDao()
        val args = CarDetailsFragmentArgs.fromBundle(requireArguments())
        factory = CarDetailsViewModelFactory(carDao, fuellingDao, checkpointDao, args.carId)
        carDetailsViewModel = ViewModelProvider(this, factory).get(CarDetailsViewModel::class.java)

        val binding = FragmentFuellingListBinding.inflate(inflater, container, false)

        val adapter = FuellingListAdapter()
        binding.fuellingList.adapter = adapter
        carDetailsViewModel.fuellingList.observe(viewLifecycleOwner) { fuellingList ->
            adapter.submitList(fuellingList)
        }

        binding.btnApplyFilter.setOnClickListener {
            val minAmount = binding.inputAmountMin.text?.toString()?.toDoubleOrNull() ?: 0.0
            val maxAmount = binding.inputAmountMax.text?.toString()?.toDoubleOrNull() ?: Double.MAX_VALUE
            val minCost = binding.inputCostMin.text?.toString()?.toDoubleOrNull() ?: 0.0
            val maxCost = binding.inputCostMax.text?.toString()?.toDoubleOrNull() ?: Double.MAX_VALUE
            carDetailsViewModel.reloadFuellingList(minAmount, maxAmount, minCost, maxCost)
        }

        binding.btnClearFilters.setOnClickListener {
            binding.inputAmountMin.text.clear()
            binding.inputAmountMax.text.clear()
            binding.inputCostMin.text.clear()
            binding.inputCostMax.text.clear()
            carDetailsViewModel.reloadFuellingList(0.0, Double.MAX_VALUE, 0.0, Double.MAX_VALUE)
        }

        binding.btnClearFuelling.setOnClickListener {
            carDetailsViewModel.deleteFuellingList()
        }

        return binding.root
    }
}
