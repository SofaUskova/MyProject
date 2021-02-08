package com.example.myapplication

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.myapplication.models.Horse
import java.io.IOException
import kotlin.concurrent.thread

class HorsePagingSource(
    private val appDatabase: AppDatabase
) : PagingSource<Int, Horse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Horse> {
        return try {
            val nextKey = params.key ?: 1

            LoadResult.Page(
                data = appDatabase.daoHorse().getAll(),
                prevKey = if (nextKey == 1) null else nextKey - 1,
                nextKey = if (nextKey == 10) null else nextKey + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Horse>): Int? {
        TODO("Not yet implemented")
    }
}