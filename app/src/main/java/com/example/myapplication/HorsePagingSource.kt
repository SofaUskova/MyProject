package com.example.myapplication

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.myapplication.models.Horse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.io.IOException

class HorsePagingSource(
    private val appDatabase: AppDatabase
) : PagingSource<Int, Horse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Horse> {
        return try {
            val nextKey = params.key ?: 1

            val list = withContext(Dispatchers.IO) {
                delay(2000)
                appDatabase.daoHorse().getAll()
            }

            LoadResult.Page(
                data = list,
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