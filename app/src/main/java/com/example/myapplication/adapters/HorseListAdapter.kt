package com.example.myapplication.adapters

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.myapplication.HorseViewHolder
import com.example.myapplication.models.Horse

class HorseListAdapter : PagingDataAdapter<Horse, HorseViewHolder>(REPO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorseViewHolder {
        return HorseViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: HorseViewHolder, position: Int) {
        val repoItem = getItem(position)
        if (repoItem != null) {
            holder.bind(repoItem)
            holder.initListeners(repoItem)
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

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<Horse>() {
            override fun areItemsTheSame(oldItem: Horse, newItem: Horse): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Horse, newItem: Horse): Boolean =
                oldItem == newItem
        }
    }
}
