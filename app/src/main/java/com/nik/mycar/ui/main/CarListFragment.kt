package com.nik.mycar.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelStore
import androidx.navigation.fragment.navArgs
import com.nik.mycar.adapters.CarListAdapter
import com.nik.mycar.databinding.FragmentCarListBinding
import com.nik.mycar.viewmodels.CarVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CarListFragment : Fragment() {

    private val carVM: CarVM by viewModels()
    private val args: CarListFragmentArgs? by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentCarListBinding.inflate(inflater, container, false)

        val adapter = CarListAdapter()
        binding.carList.adapter = adapter
        carVM.cars.observe(viewLifecycleOwner) { cars ->
            adapter.submitList(cars)
        }

        binding.btnAddCar.setOnClickListener{ _ ->
            if (binding.newCarName.visibility == View.INVISIBLE) {
                binding.newCarName.visibility = View.VISIBLE
            }
            else {
                val newCarName = binding.newCarName.text.toString()
                carVM.addCar(newCarName)
                binding.newCarName.text.clear()
                binding.newCarName.visibility = View.INVISIBLE
            }
        }

        //setHasOptionsMenu(true)
        return binding.root
    }


}
