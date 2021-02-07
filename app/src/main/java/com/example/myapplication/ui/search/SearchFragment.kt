package com.example.myapplication.ui.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.AppDatabase
import com.example.myapplication.DatabaseBuilder
import com.example.myapplication.R
import com.example.myapplication.adapters.RVAdapter
import com.example.myapplication.models.Horse
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

class SearchFragment : Fragment() {

    private lateinit var searchFragmentViewModel: SearchFragmentViewModel
    private lateinit var adapter: RVAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        searchFragmentViewModel = ViewModelProvider(this).get(SearchFragmentViewModel::class.java)
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val database = DatabaseBuilder.getInstance(requireActivity().application)

        //addAllDatabase(database)
        initAdapter()
        search(database)
    }

    private fun addAllDatabase(database: AppDatabase) {
        val list = mutableListOf<Horse>()
        for (i in 1..100) {
            list.add(Horse(i, "name$i", "2010г.р.", "mother", "father", "color","location", "200 000", true))
        }

        thread {
            database.daoHorse().insertAll(list)
        }
    }

    private fun initAdapter() {
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = RVAdapter()
        recyclerView.adapter = adapter
    }

    //TODO 4
    private var searchJob: Job? = null
    private fun search(database: AppDatabase) {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            searchFragmentViewModel.getSearchResultStream(database).collectLatest {
                Log.e("TAG", "loadPage")
                adapter.submitData(it)
            }
        }
    }
}