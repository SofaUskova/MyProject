package com.example.myapplication.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.models.Horse

class SearchFragmentViewModel : ViewModel() {

    /**
     * Лист, хранящий список продаваемых лошадей
     */
    val dataList = MutableLiveData<MutableList<Horse>>()

    init {
        dataList.postValue(Horse.initializeData())
    }
}