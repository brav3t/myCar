package com.nik.mycar.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.nik.mycar.adapters.CheckpointListAdapter
import com.nik.mycar.data.AppDatabase
import com.nik.mycar.databinding.FragmentCheckpointListBinding
import com.nik.mycar.viewmodels.CarDetailsViewModel
import com.nik.mycar.viewmodels.CarDetailsViewModelFactory
import kotlinx.android.synthetic.main.fragment_checkpoint_list.*

class CheckpointListFragment : Fragment() {

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

        val binding = FragmentCheckpointListBinding.inflate(inflater, container, false)

        val adapter = CheckpointListAdapter()
        binding.checkpointList.adapter = adapter
        carDetailsViewModel.checkpointList.observe(viewLifecycleOwner) {checkpointList ->
            adapter.submitList(checkpointList)
        }

        binding.btnReverseOrder.setOnClickListener {

        }

        binding.btnClearCheckpoints.setOnClickListener {
            carDetailsViewModel.deleteCheckpointList()
            Toast.makeText(context, "Checkpoints cleared", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }
}
