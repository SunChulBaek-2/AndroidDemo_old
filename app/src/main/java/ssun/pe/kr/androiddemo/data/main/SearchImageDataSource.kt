package ssun.pe.kr.androiddemo.data.main

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import ssun.pe.kr.androiddemo.domain.main.SearchRepository
import ssun.pe.kr.androiddemo.model.ImageItem
import ssun.pe.kr.androiddemo.presentation.NetworkState

class SearchImageDataSource(
    private val scope: CoroutineScope,
    private val query: String
) : PageKeyedDataSource<Long, ImageItem>() {

    private val repository: SearchRepository = DefaultSearchRepository()

    /**
     * There is no sync on the state because paging will always call loadInitial first then wait
     * for it to return some success value before calling loadAfter and we don't support loadBefore
     * in this example.
     * <p>
     * See BoundaryCallback example for a more complete example on syncing multiple network states.
     */
    val networkState = MutableLiveData<NetworkState>()

    val initialLoad = MutableLiveData<NetworkState>()

    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Long, ImageItem>
    ) {
        scope.launch(Dispatchers.IO) {
            // update network states.
            // we also provide an initial load state to the listeners so that the UI can know when the
            // very first list is loaded.
            networkState.postValue(NetworkState.LOADING)
            initialLoad.postValue(NetworkState.LOADING)
            try {
                val result = repository.searchImage(query, params.requestedLoadSize, 1, "sim", "all")
                callback.onResult(result.items, null, 1L + result.items.size)
                networkState.postValue(NetworkState.LOADED)
                initialLoad.postValue(NetworkState.LOADED)
            } catch (e: HttpException) {
                // publish the error
                val error = NetworkState.error("error code: ${e.code()}")
                networkState.postValue(error)
                initialLoad.postValue(error)
                e.printStackTrace()
            } catch (e: Exception) {
                // publish the error
                val error = NetworkState.error(e.message ?: "unknown error")
                networkState.postValue(error)
                initialLoad.postValue(error)
                e.printStackTrace()
            }
        }
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, ImageItem>) {
        scope.launch(Dispatchers.IO) {
            // set network value to loading.
            networkState.postValue(NetworkState.LOADING)
            try {
                val result = repository.searchImage(query, params.requestedLoadSize, params.key, "sim", "all")
                callback.onResult(result.items, params.key + result.items.size)
                networkState.postValue(NetworkState.LOADED)
            } catch (e: HttpException) {
                networkState.postValue(NetworkState.error("error code: ${e.code()}"))
                e.printStackTrace()
            } catch (e: Exception) {
                networkState.postValue(NetworkState.error(e.message ?: "unknown error"))
                e.printStackTrace()
            }
        }
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, ImageItem>) {
        // nothing to do
    }
}