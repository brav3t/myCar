package com.nik.mycar.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.nik.mycar.adapters.CarAdapter
import com.nik.mycar.databinding.FragmentCarsBinding
import com.nik.mycar.viewmodels.CarViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CarsFragment : Fragment() {

    private val carViewModel: CarViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentCarsBinding.inflate(inflater, container, false)
        context ?: return binding.root

        val adapter = CarAdapter()
        binding.carList.adapter = adapter
        subscribeUi(adapter)

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

        setHasOptionsMenu(true)
        return binding.root
    }

    private fun subscribeUi(adapter: CarAdapter) {
        carViewModel.cars.observe(viewLifecycleOwner) { cars ->
            adapter.submitList(cars)
        }
    }
}
