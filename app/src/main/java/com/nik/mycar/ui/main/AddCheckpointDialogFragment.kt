package com.nik.mycar.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.nik.mycar.data.AppDatabase
import com.nik.mycar.databinding.DialogAddCheckpointBinding
import com.nik.mycar.viewmodels.CarDetailsViewModel
import com.nik.mycar.viewmodels.CarDetailsViewModelFactory
import kotlinx.android.synthetic.main.dialog_add_checkpoint.*
import kotlinx.android.synthetic.main.dialog_add_fuelling.*

class AddCheckpointDialogFragment : DialogFragment() {

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
        val checkpointDao = AppDatabase.getInstance(application).checkpointDao()
        val args = CarDetailsFragmentArgs.fromBundle(requireArguments())
        factory = CarDetailsViewModelFactory(carDao, fuellingDao, checkpointDao, args.carId)
        carDetailsViewModel = ViewModelProvider(this, factory).get(CarDetailsViewModel::class.java)

        val binding = DialogAddCheckpointBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_add_checkpoint.setOnClickListener {
            val checkpointStr: String? = input_checkpoint.text?.toString()
            if(checkpointStr.isNullOrEmpty()) {
                Toast.makeText(context, "Checkpoint is empty!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else if (checkpointStr.toInt() <= 0) {
                Toast.makeText(context, "Checkpoint cannot be less or equal to zero!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            carDetailsViewModel.lastCheckpoint.observe(viewLifecycleOwner) { lastCheckpoint ->
                carDetailsViewModel.lastCheckpoint.removeObservers(viewLifecycleOwner)
                if (checkpointStr.toInt() < lastCheckpoint ?: 0) {
                    Toast.makeText(context, "New checkpoint cannot be less than previous value", Toast.LENGTH_SHORT).show()
                    return@observe
                }
                carDetailsViewModel.addCheckpoint(checkpointStr.toInt())
                Toast.makeText(context, "Checkpoint added!", Toast.LENGTH_SHORT).show()

                dismiss()
            }
        }
    }
}