package com.example.myapplication.ui.search.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.myapplication.db.AppDatabase
import com.example.myapplication.HorsePagingSource
import com.example.myapplication.models.Horse
import com.example.myapplication.models.UiModel
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
                enablePlaceholders = false,
                maxSize = 15
            ),
            pagingSourceFactory = {
                HorsePagingSource(appDatabase)
            }
        ).flow
    }
}