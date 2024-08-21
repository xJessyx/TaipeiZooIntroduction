package com.example.taipeizoointroduction.areaDeatil

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.taipeizoointroduction.R
import com.example.taipeizoointroduction.data.ViewItems
import com.example.taipeizoointroduction.data.animal.ResultX
import com.example.taipeizoointroduction.data.home.Results
import com.example.taipeizoointroduction.databinding.ItemAreaOverviewBinding
import com.example.taipeizoointroduction.databinding.ItemOverviewBinding
import com.example.taipeizoointroduction.databinding.ItemTitleBinding
import com.example.taipeizoointroduction.enum.AreaDetailViewEnum
import com.example.taipeizoointroduction.utils.loadImage

class AreaDetailAdapter(
    private val onClickListener: OnClickListener,
) : ListAdapter<ViewItems, RecyclerView.ViewHolder>(DiffCallback()) {

    class AnimalListViewHolder(private val binding: ItemOverviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ResultX) {
            loadImage(binding.imgItemPic, item.a_pic01_url)
            binding.tvItemTitle.text = item.a_name_ch
            binding.tvItemInfo.text = item.a_feature
            binding.dividerView.visibility = View.VISIBLE
        }

        companion object {
            fun from(parent: ViewGroup): AnimalListViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemOverviewBinding.inflate(layoutInflater, parent, false)
                return AnimalListViewHolder(binding)
            }
        }
    }

    class MainViewHolder(val binding: ItemAreaOverviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Results) {
            loadImage(binding.imgAreaMain, item.e_pic_url)
            binding.tvAreaInfo.text = item.e_info
            binding.tvAreaMemo.text = item.e_memo
            binding.tvAreaCategory.text = item.e_category
        }

        companion object {
            fun from(parent: ViewGroup): MainViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemAreaOverviewBinding.inflate(layoutInflater, parent, false)
                return MainViewHolder(binding)
            }
        }
    }

    class AnimalTitleViewHolder private constructor(val binding: ItemTitleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): AnimalTitleViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemTitleBinding.inflate(layoutInflater, parent, false)
                return AnimalTitleViewHolder(binding)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is ViewItems.MainView -> AreaDetailViewEnum.MAIN_VIEW.settingLayoutEnum
            is ViewItems.AnimalTitle -> AreaDetailViewEnum.ANIMAL_TITLE.settingLayoutEnum
            is ViewItems.AnimalList -> AreaDetailViewEnum.ANIMALS.settingLayoutEnum
            else -> throw IllegalArgumentException("Unknown item type")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            AreaDetailViewEnum.MAIN_VIEW.settingLayoutEnum -> MainViewHolder.from(parent)
            AreaDetailViewEnum.ANIMAL_TITLE.settingLayoutEnum -> AnimalTitleViewHolder.from(parent)
            AreaDetailViewEnum.ANIMALS.settingLayoutEnum -> AnimalListViewHolder.from(parent)
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when (holder.itemViewType) {
            AreaDetailViewEnum.MAIN_VIEW.settingLayoutEnum -> {
                holder as MainViewHolder
                holder.bind((item as ViewItems.MainView).main)
                holder.binding.tvAreaLink.setOnClickListener {
                    val link = hashMapOf<String, Any>("link" to item.main.e_url)
                    onClickListener.onClick(link)
                }
            }

            AreaDetailViewEnum.ANIMAL_TITLE.settingLayoutEnum -> {
                holder as AnimalTitleViewHolder
                val context = holder.binding.root.context
                holder.binding.tvTitle.text = context.getString(R.string.animal_title)
            }

            AreaDetailViewEnum.ANIMALS.settingLayoutEnum -> {
                holder as AnimalListViewHolder
                holder.bind((item as ViewItems.AnimalList).animalData)
                holder.itemView.setOnClickListener {
                    val data = hashMapOf<String, Any>("animal" to item.animalData)
                    onClickListener.onClick(data)
                }
            }

            else -> throw IllegalArgumentException("Invalid ViewHolder type")
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<ViewItems>() {
        override fun areItemsTheSame(oldItem: ViewItems, newItem: ViewItems): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ViewItems, newItem: ViewItems): Boolean {
            return oldItem == newItem
        }
    }

    class OnClickListener(val clickListener: ((item: HashMap<String, Any>) -> Unit)) {
        fun onClick(item: HashMap<String, Any>) = clickListener(item)
    }

}
