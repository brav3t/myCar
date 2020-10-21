package com.nik.mycar.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nik.mycar.data.Fuelling
import com.nik.mycar.databinding.ListItemFuellingBinding
import java.text.NumberFormat
import java.util.*
import java.util.Calendar.*

class FuellingListAdapter : ListAdapter<Fuelling, RecyclerView.ViewHolder>(FuellingDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FuellingItemViewHolder(
            ListItemFuellingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val fuelling = getItem(position)
        (holder as FuellingItemViewHolder).bind(fuelling)
    }

    class FuellingItemViewHolder(
        private val binding: ListItemFuellingBinding
    ) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: Fuelling) {
            binding.apply {

                var dateStr: String =
                    item.date.get(YEAR).toString() + "-" +
                    item.date.getDisplayName(MONTH, LONG, Locale("hu", "HU")) + "-" +
                    item.date.get(DAY_OF_MONTH).toString()
                date = dateStr
                amount = item.amount.toString() + " liter"
                cost = NumberFormat.getCurrencyInstance(Locale("hu", "HU")).format(item.cost)

                executePendingBindings()
            }
        }
    }
}

private class FuellingDiffCallback : DiffUtil.ItemCallback<Fuelling>() {

    override fun areItemsTheSame(oldItem: Fuelling, newItem: Fuelling): Boolean {
        return oldItem.fuellingId == newItem.fuellingId
    }

    override fun areContentsTheSame(oldItem: Fuelling, newItem: Fuelling): Boolean {
        return oldItem == newItem
    }
}
