package com.nik.mycar.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nik.mycar.data.Service
import com.nik.mycar.databinding.ListItemServiceBinding
import java.text.NumberFormat
import java.util.*
import java.util.Calendar.*

class ServiceListAdapter : ListAdapter<Service, RecyclerView.ViewHolder>(ServiceDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ServiceItemViewHolder(
            ListItemServiceBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val service = getItem(position)
        (holder as ServiceItemViewHolder).bind(service)
    }

    class ServiceItemViewHolder(
        private val binding: ListItemServiceBinding
    ) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: Service) {
            binding.apply {

                var dateStr: String =
                    item.date.get(YEAR).toString() + "-" +
                            item.date.getDisplayName(MONTH, LONG, Locale("hu", "HU")) + "-" +
                            item.date.get(DAY_OF_MONTH).toString()
                date = dateStr

                description = item.description

                cost = NumberFormat.getCurrencyInstance(Locale("hu", "HU")).format(item.cost)

                executePendingBindings()
            }
        }
    }
}

private class ServiceDiffCallback : DiffUtil.ItemCallback<Service>() {

    override fun areItemsTheSame(oldItem: Service, newItem: Service): Boolean {
        return oldItem.serviceId == newItem.serviceId
    }

    override fun areContentsTheSame(oldItem: Service, newItem: Service): Boolean {
        return oldItem == newItem
    }
}
