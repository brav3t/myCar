package com.nik.mycar.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.nik.mycar.adapters.CarListAdapter
import com.nik.mycar.databinding.FragmentCarListBinding
import com.nik.mycar.viewmodels.CarListVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CarListFragment : Fragment() {

    private val carListVM: CarListVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentCarListBinding.inflate(inflater, container, false)

        val adapter = CarListAdapter()
        binding.carList.adapter = adapter
        carListVM.cars.observe(viewLifecycleOwner) { cars ->
            adapter.submitList(cars)
        }

        binding.btnAddCar.setOnClickListener{ _ ->
            if (binding.newCarName.visibility == View.INVISIBLE) {
                binding.newCarName.visibility = View.VISIBLE
            }
            else {
                val newCarName = binding.newCarName.text.toString()
                carListVM.addCar(newCarName)
                binding.newCarName.text.clear()
                binding.newCarName.visibility = View.INVISIBLE
            }
        }

        setHasOptionsMenu(true)
        return binding.root
    }
}
