package com.nik.mycar.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.nik.mycar.data.AppDatabase
import com.nik.mycar.databinding.AddFuellingDialogBinding
import com.nik.mycar.viewmodels.CarDetailsViewModel
import com.nik.mycar.viewmodels.CarDetailsViewModelFactory
import kotlinx.android.synthetic.main.add_fuelling_dialog.*

class AddFuellingDialogFragment : DialogFragment() {

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

        val binding = AddFuellingDialogBinding.inflate(inflater, container, false)
        binding.viewModel = carDetailsViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.setOnShowListener{
            dialog?.setTitle("Add Fuelling")
        }

        btn_add_fuelling.setOnClickListener {
            val amountStr: String? = input_amount.text?.toString()
            if(amountStr.isNullOrEmpty()) {
                Toast.makeText(context, "Amount is empty!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val costStr: String? = input_cost.text?.toString()
            if(costStr.isNullOrEmpty()) {
                Toast.makeText(context, "Cost is empty!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val amount = amountStr.toDouble()
            val cost  = costStr.toDouble()
            if(amount != null && cost != null) {
                carDetailsViewModel.addFuelling(amount, cost)
                Toast.makeText(context, "Fuelling is added!", Toast.LENGTH_SHORT).show()
            }
            dismiss()
        }
    }
}