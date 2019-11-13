package ssun.pe.kr.androiddemo.data

import androidx.paging.PageKeyedDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ssun.pe.kr.androiddemo.data.model.Item
import ssun.pe.kr.androiddemo.network.NaverService
import ssun.pe.kr.androiddemo.network.RetrofitCreator

class NaverDataSource(
    private val scope: CoroutineScope,
    private val query: String
) : PageKeyedDataSource<Long, Item>() {

    private val naverService: NaverService = RetrofitCreator.create().create(NaverService::class.java)

    override fun loadInitial(params: LoadInitialParams<Long>, callback: LoadInitialCallback<Long, Item>) {
        try {
            scope.launch {
                if (query.isNotBlank()) {
                    try {
                        val result = naverService.searchShop(query, params.requestedLoadSize, 1, "sim").await()
                        callback.onResult(result.items, null, 1L + result.items.size)
                    } catch (e: Exception) {
                        // nothing to do
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, Item>) {
        try {
            scope.launch {
                if (query.isNotBlank()) {
                    try {
                        val result = naverService.searchShop(query, params.requestedLoadSize, params.key, "sim").await()
                        callback.onResult(result.items, params.key + result.items.size)
                    } catch (e: Exception) {
                        // nothing to do
                    }
                }
            }
        } catch (e: Exception) {
             e.printStackTrace()
        }
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, Item>) {
        // nothing to do
    }
}