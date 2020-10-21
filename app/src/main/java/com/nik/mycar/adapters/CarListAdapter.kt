package com.nik.mycar.adapters

import android.view.LayoutInflater
import android.view.View
import com.nik.mycar.data.Car
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nik.mycar.databinding.ListItemCarBinding
import com.nik.mycar.ui.main.CarListFragmentDirections

class CarListAdapter : ListAdapter<Car, RecyclerView.ViewHolder>(CarDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CarItemViewHolder(
            ListItemCarBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val car = getItem(position)
        (holder as CarItemViewHolder).bind(car)
    }

    class CarItemViewHolder(
        private val binding: ListItemCarBinding
    ) : RecyclerView.ViewHolder(binding.root){
        init {
            binding.setClickListener {
                binding.car?.let { car ->
                    navigateToCar(car, it)
                }
            }
        }

        private fun navigateToCar(car: Car, view: View) {
            val direction = CarListFragmentDirections.actionCarListFragmentToCarDetailsFragment(car.carId)
            view.findNavController().navigate(direction)
        }

        fun bind(item: Car) {
            binding.apply {
                car = item
                executePendingBindings()
            }
        }
    }
}

private class CarDiffCallback : DiffUtil.ItemCallback<Car>() {

    override fun areItemsTheSame(oldItem: Car, newItem: Car): Boolean {
        return oldItem.carId == newItem.carId
    }

    override fun areContentsTheSame(oldItem: Car, newItem: Car): Boolean {
        return oldItem == newItem
    }
}