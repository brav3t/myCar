package com.nik.mycar.adapters

import android.view.LayoutInflater
import com.nik.mycar.data.Car
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nik.mycar.databinding.CarListItemBinding

class CarAdapter : ListAdapter<Car, RecyclerView.ViewHolder>(CarDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CarViewHolder(
            CarListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val car = getItem(position)
        (holder as CarViewHolder).bind(car)
    }

    class CarViewHolder(
        private val binding: CarListItemBinding
    ) : RecyclerView.ViewHolder(binding.root){
        init {

        }

        private fun navigateToCar(
        ){}

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