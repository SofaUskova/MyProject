package com.example.myapplication.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.adapters.RVAdapter
import com.example.myapplication.models.Horse

class FavoriteFragment : Fragment() {

    private lateinit var favoriteFragmentViewModel: FavoriteFragmentViewModel
    private lateinit var adapter: RVAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        favoriteFragmentViewModel = ViewModelProviders.of(this).get(FavoriteFragmentViewModel::class.java)
        return inflater.inflate(R.layout.fragment_favourite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}