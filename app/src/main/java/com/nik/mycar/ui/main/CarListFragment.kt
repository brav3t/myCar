package com.nik.mycar.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.nik.mycar.adapters.CarListAdapter
import com.nik.mycar.data.AppDatabase
import com.nik.mycar.databinding.FragmentCarListBinding
import com.nik.mycar.viewmodels.CarViewModel
import com.nik.mycar.viewmodels.CarViewModelFactory

class CarListFragment : Fragment() {

    private lateinit var carViewModel: CarViewModel
    private lateinit var factory: CarViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val application = requireNotNull(this.activity).application
        val carDao = AppDatabase.getInstance(application).carDao()
        factory = CarViewModelFactory(carDao)
        carViewModel = ViewModelProvider(this, factory).get(CarViewModel::class.java)

        val binding = FragmentCarListBinding.inflate(inflater, container, false)

        val adapter = CarListAdapter()
        binding.carList.adapter = adapter
        carViewModel.cars.observe(viewLifecycleOwner) { cars ->
            adapter.submitList(cars)
        }

        binding.btnAddCar.setOnClickListener{ _ ->
            if (binding.newCarName.visibility == View.INVISIBLE) {
                binding.newCarName.visibility = View.VISIBLE
            }
            else {
                val newCarName = binding.newCarName.text.toString()
                carViewModel.addCar(newCarName)
                binding.newCarName.text.clear()
                binding.newCarName.visibility = View.INVISIBLE
            }
        }

        //setHasOptionsMenu(true)
        return binding.root
    }
}
