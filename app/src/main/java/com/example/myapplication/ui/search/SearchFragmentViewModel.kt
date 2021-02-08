package com.example.myapplication.ui.search

import androidx.lifecycle.ViewModel
import androidx.paging.*
import com.example.myapplication.AppDatabase
import com.example.myapplication.HorsePagingSource
import com.example.myapplication.models.Horse
import kotlinx.coroutines.flow.Flow

class SearchFragmentViewModel : ViewModel() {

    fun getSearchResultStream(appDatabase: AppDatabase): Flow<PagingData<Horse>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = true
            ),
            pagingSourceFactory = {
                HorsePagingSource(appDatabase)
            }
        ).flow
    }
}