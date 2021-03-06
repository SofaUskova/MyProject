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
import com.example.myapplication.db.AppDatabase
import com.example.myapplication.db.DatabaseBuilder
import com.example.myapplication.R
import com.example.myapplication.adapters.HorsePagingDataAdapter
import com.example.myapplication.adapters.HorseLoadStateAdapter
import com.example.myapplication.models.Horse
import com.example.myapplication.ui.interfaces.OnActivityDataListener
import com.example.myapplication.ui.search.viewModels.SearchFragmentViewModel
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

class SearchFragment : Fragment(), OnActivityDataListener {

    private lateinit var searchFragmentViewModel: SearchFragmentViewModel
    private lateinit var horsePagingDataAdapter: HorsePagingDataAdapter
    private var searchJob: Job? = null

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

        searchFragmentViewModel.database = DatabaseBuilder.getInstance(requireActivity().application)

        //addAllDatabase(database)
        initAdapter()
        searchData(searchFragmentViewModel.database)
    }

    private fun addAllDatabase(database: AppDatabase) {
        val list = mutableListOf<Horse>()
        for (i in 1..10) {
            list.add(Horse(i, "name$i", "2010г.р.", "mother", "father", "color","location", "20000$i", true))
        }

        thread {
            database.daoHorse().insertAll(list)
        }
    }

    private fun initAdapter() {
        horsePagingDataAdapter = HorsePagingDataAdapter()
        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = horsePagingDataAdapter.withLoadStateHeaderAndFooter(
                header = HorseLoadStateAdapter { horsePagingDataAdapter.retry() },
                footer = HorseLoadStateAdapter { horsePagingDataAdapter.retry() }
            )
        }
        horsePagingDataAdapter.addLoadStateListener { loadState ->
            recyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
            message.isVisible = loadState.source.refresh is LoadState.Loading
            //error_msg.isVisible = loadState.source.refresh is LoadState.Error
        }
    }

    private fun searchData(database: AppDatabase) {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            searchFragmentViewModel.searchHorse(database).collectLatest {
                horsePagingDataAdapter.submitData(it)
            }
        }
    }

    fun sortedData(sortByMore: Boolean, database: AppDatabase) {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            searchFragmentViewModel.searchHorse(database, sortByMore).collectLatest {
                horsePagingDataAdapter.notifyDataSetChanged()
                horsePagingDataAdapter.submitData(it)
            }
        }
    }

    override fun onActivityDataListener(sortByMore: Boolean) {
        sortedData(sortByMore, database)
    }
}