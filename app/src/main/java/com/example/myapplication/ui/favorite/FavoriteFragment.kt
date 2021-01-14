package com.example.myapplication.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.myapplication.R

class FavoriteFragment : Fragment() {

    private lateinit var favoriteFragmentViewModel: FavoriteFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        favoriteFragmentViewModel =
            ViewModelProviders.of(this).get(FavoriteFragmentViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_favourite, container, false)
        root.findViewById<TextView>(R.id.text_dashboard).text = "избранное"
        return root
    }
}