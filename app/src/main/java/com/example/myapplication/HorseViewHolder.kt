package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.models.Horse
import kotlinx.android.synthetic.main.card_view_horse.view.*

class HorseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val name: TextView = view.findViewById(R.id.name)
    private val age: TextView = view.findViewById(R.id.age)
    private val mother: TextView = view.findViewById(R.id.mother)
    private val father: TextView = view.findViewById(R.id.father)
    private val color: TextView = view.findViewById(R.id.color)
    private val location: TextView = view.findViewById(R.id.location)
    private val price: TextView = view.findViewById(R.id.price)
    private val cardView: CardView = view.findViewById(R.id.cardView)

    private var horse: Horse? = null

    init {
        view.cardView.setOnClickListener {
           //NavHostFragment.findNavController(it.).navigate(R.id.action_navigation_search_to_detailInformationActivity)
        }

        view.cardView.scrollLayout.setOnClickListener {
           //NavHostFragment.findNavController(context!!).navigate(R.id.action_navigation_search_to_viewingImagesActivity)
        }
    }

    fun bind(horse: Horse?) {
        name.text = horse?.name
        age.text = horse?.age
        mother.text = horse?.mother
        father.text = horse?.father
        color.text = horse?.color
        location.text = horse?.location
        price.text = horse?.price
    }

    fun initListeners(horse: Horse) {
        cardView.imageButtonAddFavorite.setOnClickListener {
            if (horse.favorite) {
                cardView.imageButtonAddFavorite.setImageResource(R.drawable.ic_favorite_added)
                horse.favorite = false
            } else {
                cardView.imageButtonAddFavorite.setImageResource(R.drawable.ic_favorite)
                horse.favorite = true
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup): HorseViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.card_view_horse, parent, false)
            return HorseViewHolder(view)
        }
    }
}