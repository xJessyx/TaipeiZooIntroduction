package com.example.taipeizoointroduction.areaHome

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.taipeizoointroduction.data.home.Results
import com.example.taipeizoointroduction.databinding.ItemOverviewBinding
import com.example.taipeizoointroduction.utils.loadImage

class AreaHomeAdapter(
    private val onClickListener: OnClickListener,
) : ListAdapter<Results, AreaHomeAdapter.AreaHomeViewHolder>(DiffCallback()) {

    class AreaHomeViewHolder(private val binding: ItemOverviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Results) {
            loadImage(binding.imgItemPic, item.e_pic_url)
            binding.tvItemTitle.text = item.e_name
            binding.tvItemInfo.text = item.e_info
            binding.tvItemMemo.text = item.e_memo
        }

        companion object {
            fun from(parent: ViewGroup): AreaHomeViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemOverviewBinding.inflate(layoutInflater, parent, false)
                return AreaHomeViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AreaHomeViewHolder {
        return AreaHomeViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: AreaHomeViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(item)
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Results>() {
        override fun areItemsTheSame(oldItem: Results, newItem: Results): Boolean {
            return oldItem._id == newItem._id
        }

        override fun areContentsTheSame(oldItem: Results, newItem: Results): Boolean {
            return oldItem == newItem
        }
    }

    class OnClickListener(val clickListener: ((item: Results) -> Unit)) {
        fun onClick(item: Results) = clickListener(item)
    }
}