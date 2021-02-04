package com.example.myapplication.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.DatabaseBuilder
import com.example.myapplication.R
import com.example.myapplication.adapters.RVAdapter
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : Fragment() {

    private lateinit var searchFragmentViewModel: SearchFragmentViewModel
    private lateinit var adapter: RVAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        searchFragmentViewModel = ViewModelProvider(this).get(SearchFragmentViewModel::class.java)
        val dbHelper = DatabaseBuilder.getInstance(requireActivity().application)
        searchFragmentViewModel.fetchUsers(dbHelper)
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        appDatabase.daoHorse()
//            ?.insert(Horse(1, "name", "21", "mother", "father", "location", 200000, true))

        initObservers()
    }

    private fun initObservers() {
        searchFragmentViewModel.dataList.observe(viewLifecycleOwner, Observer {
            recyclerView.layoutManager = LinearLayoutManager(activity)
            adapter = RVAdapter(it, this)
            recyclerView.adapter = adapter
        })
    }
}