package com.example.myapplication.ui.search.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.myapplication.db.AppDatabase
import com.example.myapplication.ui.searchHorses

class SearchFragmentViewModel : ViewModel() {
    var database: AppDatabase? = null

    init {
        searchHorses(database!!, sortByMore, viewModelScope)
    }

}