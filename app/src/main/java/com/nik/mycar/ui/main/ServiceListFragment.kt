package com.nik.mycar.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.nik.mycar.adapters.ServiceListAdapter
import com.nik.mycar.data.AppDatabase
import com.nik.mycar.databinding.FragmentServiceListBinding
import com.nik.mycar.viewmodels.CarDetailsViewModel
import com.nik.mycar.viewmodels.CarDetailsViewModelFactory

class ServiceListFragment : Fragment() {

    private lateinit var carDetailsViewModel: CarDetailsViewModel
    private lateinit var factory: CarDetailsViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(this.activity).application
        val carDao = AppDatabase.getInstance(application).carDao()
        val fuellingDao = AppDatabase.getInstance(application).fuellingDao()
        val serviceDao = AppDatabase.getInstance(application).serviceDao()
        val checkpointDao = AppDatabase.getInstance(application).checkpointDao()
        val args = CarDetailsFragmentArgs.fromBundle(requireArguments())
        factory = CarDetailsViewModelFactory(carDao, fuellingDao, serviceDao, checkpointDao, args.carId)
        carDetailsViewModel = ViewModelProvider(this, factory).get(CarDetailsViewModel::class.java)

        val binding = FragmentServiceListBinding.inflate(inflater, container, false)

        val adapter = ServiceListAdapter()
        binding.serviceList.adapter = adapter
        carDetailsViewModel.serviceList.observe(viewLifecycleOwner) {serviceList ->
            adapter.submitList(serviceList)
        }

        binding.btnClearServices.setOnClickListener {
            carDetailsViewModel.deleteServiceList()
        }

        binding.btnClear.setOnClickListener {
            binding.textSearchText.text.clear()
            carDetailsViewModel.filterServicesByWord(null)
        }

        binding.btnSearch.setOnClickListener {
            carDetailsViewModel.filterServicesByWord(binding.textSearchText.text.toString())
        }

        return binding.root
    }

}