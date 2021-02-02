package com.example.myapplication.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.adapters.RVAdapter
import com.example.myapplication.models.Horse
import com.example.myapplication.ui.search.`interface`.OnActivityDataListener

class SearchFragment : Fragment(), OnActivityDataListener {

    private lateinit var homeViewModel: SearchFragmentViewModel
    private lateinit var adapter: RVAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(SearchFragmentViewModel::class.java)
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = RVAdapter(Horse.initializeData(), this)
        recyclerView.adapter = adapter
    }

    override fun onActivityDataListener(sortByMore: Boolean) {
        adapter.updateData(sortByMore)
    }
}