package ssun.pe.kr.androiddemo.data.main

import androidx.paging.PageKeyedDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ssun.pe.kr.androiddemo.domain.main.SearchRepository
import ssun.pe.kr.androiddemo.model.ImageItem

class SearchImageDataSource(
    private val scope: CoroutineScope,
    private val query: String
) : PageKeyedDataSource<Long, ImageItem>() {

    private val repository: SearchRepository = DefaultSearchRepository()

    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Long, ImageItem>
    ) {
        scope.launch {
            try {
                val result = repository.searchImage(query, params.requestedLoadSize, 1, "sim", "all")
                callback.onResult(result.items, null, 1L + result.items.size)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, ImageItem>) {
        scope.launch {
            try {
                val result = repository.searchImage(query, params.requestedLoadSize, params.key, "sim", "all")
                callback.onResult(result.items, params.key + result.items.size)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, ImageItem>) {
        // nothing to do
    }
}