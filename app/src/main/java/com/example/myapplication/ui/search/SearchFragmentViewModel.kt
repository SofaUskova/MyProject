package com.example.myapplication.ui.search

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.myapplication.AppDatabase
import com.example.myapplication.HorsePagingSource
import com.example.myapplication.models.Horse
import kotlinx.coroutines.flow.Flow
import kotlin.concurrent.thread

class SearchFragmentViewModel : ViewModel() {

    fun getSearchResultStream(appDatabase: AppDatabase): Flow<PagingData<Horse>> {
        var horses: List<Horse> = mutableListOf()

        thread {
            horses = appDatabase.daoHorse().getAll()
        }

        return Pager(
                config = PagingConfig(
                    pageSize = 10,
                    enablePlaceholders = false
                ),
                pagingSourceFactory = {
                    HorsePagingSource( horses )
                }
            ).flow
    }
}