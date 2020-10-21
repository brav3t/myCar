package com.nik.mycar.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.nik.mycar.data.AppDatabase
import com.nik.mycar.databinding.DialogAddFuellingBinding
import com.nik.mycar.viewmodels.CarDetailsViewModel
import com.nik.mycar.viewmodels.CarDetailsViewModelFactory
import kotlinx.android.synthetic.main.dialog_add_fuelling.*

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

        val binding = DialogAddFuellingBinding.inflate(inflater, container, false).apply {
            viewModel = carDetailsViewModel
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

            carDetailsViewModel.addFuelling(amountStr.toDouble(), costStr.toDouble())
            Toast.makeText(context, "Fuelling is added!", Toast.LENGTH_SHORT).show()

            dismiss()
        }
    }
}