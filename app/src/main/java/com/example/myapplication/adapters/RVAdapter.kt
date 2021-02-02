package com.example.myapplication.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.models.Horse
import com.example.myapplication.ui.search.ViewingImagesActivity
import kotlinx.android.synthetic.main.card_view_horse.view.*


class RVAdapter(
    private val horses: MutableList<Horse>,
    //TODO
    private val context: Fragment?
) : RecyclerView.Adapter<RVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.card_view_horse,
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return this.horses.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = horses[position].name
        holder.age.text = horses[position].age
        holder.mother.text = horses[position].mother
        holder.father.text = horses[position].father
        holder.location.text = horses[position].location
        holder.price.text = horses[position].price.toString()

        holder.cardView.setOnClickListener {
            NavHostFragment.findNavController(context!!).navigate(R.id.action_navigation_search_to_detailInformationActivity)
        }

        holder.cardView.imageButtonAddFavorite.setOnClickListener {
            if (!horses[position].favorite) {
                holder.cardView.imageButtonAddFavorite.setImageResource(R.drawable.ic_favorite_added)
                horses[position].favorite = true
            } else {
                holder.cardView.imageButtonAddFavorite.setImageResource(R.drawable.ic_favorite)
                horses[position].favorite = false
            }
        }

        holder.cardView.scrollLayout.setOnClickListener {
            NavHostFragment.findNavController(context!!).navigate(R.id.action_navigation_search_to_viewingImagesActivity)
        }
    }

    fun updateData(sortByMore: Boolean) {
        val sortedListHorses = if (sortByMore) {
            horses.sortedBy { it.price }
        } else {
            horses.sortedByDescending { it.price }
        }
        horses.clear()
        horses.addAll(sortedListHorses)
        notifyDataSetChanged()
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
    }

    inner class ViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {
        internal val name: TextView = view.findViewById(R.id.name)
        internal val age: TextView = view.findViewById(R.id.age)
        internal val mother: TextView = view.findViewById(R.id.mother)
        internal val father: TextView = view.findViewById(R.id.father)
        internal val location: TextView = view.findViewById(R.id.location)
        internal val price: TextView = view.findViewById(R.id.price)
        internal val cardView: CardView = view.findViewById(R.id.cardView)
    }

}
