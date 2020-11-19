package com.nik.mycar.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nik.mycar.data.Checkpoint
import com.nik.mycar.databinding.ListItemCheckpointBinding
import java.util.*
import java.util.Calendar.*

class CheckpointListAdapter : ListAdapter<Checkpoint, RecyclerView.ViewHolder>(CheckpointDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CheckpointItemViewHolder(
            ListItemCheckpointBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val checkpoint = getItem(position)
        (holder as CheckpointItemViewHolder).bind(checkpoint)
    }

    class CheckpointItemViewHolder(
        private val binding: ListItemCheckpointBinding
    ) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: Checkpoint) {
            binding.apply {

                var dateStr: String =
                    item.date.get(YEAR).toString() + "-" +
                    item.date.getDisplayName(MONTH, LONG, Locale("hu", "HU")) + "-" +
                    item.date.get(DAY_OF_MONTH).toString()
                date = dateStr
                milestone = item.checkpoint.toString() + " Km"

                executePendingBindings()
            }
        }
    }
}

private class CheckpointDiffCallback : DiffUtil.ItemCallback<Checkpoint>() {

    override fun areItemsTheSame(oldItem: Checkpoint, newItem: Checkpoint): Boolean {
        return oldItem.checkpointId == newItem.checkpointId
    }

    override fun areContentsTheSame(oldItem: Checkpoint, newItem: Checkpoint): Boolean {
        return oldItem == newItem
    }
}
