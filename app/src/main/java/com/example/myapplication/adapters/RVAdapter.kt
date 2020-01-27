package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.models.Horse

class RVAdapter(
        private val horses: List<Horse>
    ) : RecyclerView.Adapter<RVAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_horse, parent, false)
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
        }

        inner class ViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {
            internal val name: TextView = view.findViewById(R.id.name)
            internal val age: TextView = view.findViewById(R.id.age)
            internal val mother: TextView = view.findViewById(R.id.mother)
            internal val father: TextView = view.findViewById(R.id.father)
            internal val location: TextView = view.findViewById(R.id.location)
            internal val cardView: CardView = view.findViewById(R.id.cardView)
        }

}
