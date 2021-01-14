package com.example.myapplication.ui.search

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

class SearchFragment : Fragment() {

    private lateinit var homeViewModel: SearchFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProviders.of(this).get(SearchFragmentViewModel::class.java)
        return inflater.inflate(R.layout.fragment_detail_information, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
//        recyclerView.layoutManager = LinearLayoutManager(activity)
//        val adapter = RVAdapter(Horse("Клюква", "2010г.р.", "Балерина", "Карнавал - ", "КСК Отрада").initializeData())
//        recyclerView.adapter = adapter
    }
}