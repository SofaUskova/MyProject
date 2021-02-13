package com.example.myapplication.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adapters.HorseLoadStateAdapter
import com.example.myapplication.adapters.HorsePagingDataAdapter
import com.example.myapplication.db.AppDatabase
import com.example.myapplication.db.DatabaseBuilder
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FavoriteFragment : Fragment() {

    private lateinit var favoriteFragmentViewModel: FavoriteFragmentViewModel
    private lateinit var horsePagingDataAdapter: HorsePagingDataAdapter
    private lateinit var database: AppDatabase
    private var searchJob: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        favoriteFragmentViewModel = ViewModelProvider(this).get(FavoriteFragmentViewModel::class.java)
        return inflater.inflate(R.layout.fragment_favourite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        database = DatabaseBuilder.getInstance(requireActivity().application)

        initAdapter()
        searchData(database)
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
            favoriteFragmentViewModel.searchHorse(database).collectLatest {
                horsePagingDataAdapter.submitData(it)
            }
        }
    }
}