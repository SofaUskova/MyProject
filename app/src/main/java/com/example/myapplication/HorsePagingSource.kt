package com.example.myapplication

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.myapplication.models.Horse
import java.io.IOException

//TODO 2
class HorsePagingSource(
    private val listHorses: List<Horse>
) : PagingSource<Int, Horse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Horse> {
        return try {
            val nextKey = params.key ?: 1
            LoadResult.Page(
                data = listHorses,
                prevKey = if (nextKey == 1) null else nextKey - 1,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Horse>): Int? {
        TODO("Not yet implemented")
    }
}