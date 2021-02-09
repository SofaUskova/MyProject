package com.example.myapplication.adapters

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.HorseViewHolder
import com.example.myapplication.R
import com.example.myapplication.SeparatorViewHolder
import com.example.myapplication.ui.UiModel

class HorseListAdapter : PagingDataAdapter<UiModel, RecyclerView.ViewHolder>(UI_MODEL_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == R.layout.card_view_horse) {
            HorseViewHolder.create(parent)
        } else {
            SeparatorViewHolder.create(parent)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is UiModel.HorseItem -> R.layout.card_view_horse
            is UiModel.SeparatorItem -> R.layout.separator_view_item
            null -> throw UnsupportedOperationException("Unknown view")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val uiModel = getItem(position)
        uiModel.let {
            when (uiModel) {
                is UiModel.HorseItem -> (holder as HorseViewHolder).bind(uiModel.horse)
                is UiModel.SeparatorItem -> (holder as SeparatorViewHolder).bind(uiModel.description)
            }
        }
    }

    companion object {
        private val UI_MODEL_COMPARATOR = object : DiffUtil.ItemCallback<UiModel>() {
            override fun areItemsTheSame(oldItem: UiModel, newItem: UiModel): Boolean {
                return (oldItem is UiModel.HorseItem && newItem is UiModel.HorseItem &&
                        oldItem.horse.id == newItem.horse.id) ||
                        (oldItem is UiModel.SeparatorItem && newItem is UiModel.SeparatorItem &&
                                oldItem.description == newItem.description)
            }

            override fun areContentsTheSame(oldItem: UiModel, newItem: UiModel): Boolean =
                oldItem == newItem
        }
    }

//    fun updateData(sortByMore: Boolean) {
//        val sortedListHorses = if (sortByMore) {
//            horses.sortedBy { it.price }
//        } else {
//            horses.sortedByDescending { it.price }
//        }
//        horses.clear()
//        horses.addAll(sortedListHorses)
//        notifyDataSetChanged()
//    }
}
