package com.example.myapplication.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.AppDatabase
import com.example.myapplication.DatabaseBuilder
import com.example.myapplication.R
import com.example.myapplication.adapters.HorseListAdapter
import com.example.myapplication.adapters.HorseLoadStateAdapter
import com.example.myapplication.models.Horse
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

class SearchFragment : Fragment() {

    private lateinit var searchFragmentViewModel: SearchFragmentViewModel
    private lateinit var horseListAdapter: HorseListAdapter

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
        for (i in 1..10) {
            list.add(Horse(i, "name$i", "2010г.р.", "mother", "father", "color","location", "200 000", true))
        }

        thread {
            database.daoHorse().insertAll(list)
        }
    }

    private fun initAdapter() {
        horseListAdapter = HorseListAdapter()
        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = horseListAdapter.withLoadStateHeaderAndFooter(
                header = HorseLoadStateAdapter { horseListAdapter.retry() },
                footer = HorseLoadStateAdapter { horseListAdapter.retry() }
            )
        }
        horseListAdapter.addLoadStateListener { loadState ->
            recyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
            message.isVisible = loadState.source.refresh is LoadState.Loading
            //error_msg.isVisible = loadState.source.refresh is LoadState.Error
        }
    }

    private var searchJob: Job? = null
    private fun search(database: AppDatabase) {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            searchFragmentViewModel.searchHorse(database).collectLatest {
                horseListAdapter.submitData(it)
            }
        }
    }
}