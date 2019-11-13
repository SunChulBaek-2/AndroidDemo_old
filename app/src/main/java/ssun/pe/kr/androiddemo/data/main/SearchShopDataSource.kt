package ssun.pe.kr.androiddemo.data.main

import androidx.paging.PageKeyedDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ssun.pe.kr.androiddemo.domain.main.SearchShopRepository
import ssun.pe.kr.androiddemo.model.Item

class SearchShopDataSource(
    private val scope: CoroutineScope,
    private val query: String
) : PageKeyedDataSource<Long, Item>() {

    private val repository: SearchShopRepository = DefaultSearchShopRepository()

    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Long, Item>
    ) {
        scope.launch {
            try {
                val result = repository.searchShop(query, params.requestedLoadSize, 1, "sim")
                callback.onResult(result.items, null, 1L + result.items.size)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, Item>) {
        scope.launch {
            try {
                val result = repository.searchShop(
                    query,
                    params.requestedLoadSize,
                    params.key,
                    "sim"
                )
                callback.onResult(result.items, params.key + result.items.size)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, Item>) {
        // nothing to do
    }
}