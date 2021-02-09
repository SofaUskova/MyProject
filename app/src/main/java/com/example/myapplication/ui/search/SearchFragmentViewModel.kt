package com.example.myapplication.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.myapplication.AppDatabase
import com.example.myapplication.HorsePagingSource
import com.example.myapplication.models.Horse
import com.example.myapplication.ui.UiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SearchFragmentViewModel : ViewModel() {

    fun searchHorse(appDatabase: AppDatabase): Flow<PagingData<UiModel>> {
        return getSearchResultStream(appDatabase)
            .map { pagingData -> pagingData.map { UiModel.HorseItem(it) } }
            .map {
                it.insertSeparators <UiModel.HorseItem, UiModel>{ before, after ->
                    if (after == null) {
                        return@insertSeparators UiModel.SeparatorItem("конец неизбежен")
                    }

                    if (before == null) {
                        return@insertSeparators UiModel.SeparatorItem("начало списка")
                    } else {
                        // no separator
                        null
                    }
                }
            }
            .cachedIn(viewModelScope)
    }

    private fun getSearchResultStream(appDatabase: AppDatabase): Flow<PagingData<Horse>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                HorsePagingSource(appDatabase)
            }
        ).flow
    }
}