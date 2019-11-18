package ssun.pe.kr.androiddemo.data.main

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import ssun.pe.kr.androiddemo.domain.main.SearchRepository
import ssun.pe.kr.androiddemo.model.ShopItem
import ssun.pe.kr.androiddemo.presentation.NetworkState

class SearchShopDataSource(
    private val disposables: CompositeDisposable,
    private val query: String
) : PageKeyedDataSource<Long, ShopItem>() {

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
        callback: LoadInitialCallback<Long, ShopItem>
    ) {
        repository.searchShop(query, params.requestedLoadSize, 1, "sim")
            .subscribeOn(Schedulers.io())
            .doOnSuccess {
                // update network states.
                // we also provide an initial load state to the listeners so that the UI can know when the
                // very first list is loaded.
                networkState.postValue(NetworkState.LOADING)
                initialLoad.postValue(NetworkState.LOADING)
            }.observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(onSuccess = { result ->
                callback.onResult(result.items, null, 1L + result.items.size)
                networkState.postValue(NetworkState.LOADED)
                initialLoad.postValue(NetworkState.LOADED)
            }, onError = { e ->
                // publish the error
                val error = NetworkState.error(e.message ?: "unknown error")
                networkState.postValue(error)
                initialLoad.postValue(error)
                e.printStackTrace()
            }).addTo(disposables)
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, ShopItem>) {
        repository.searchShop(query, params.requestedLoadSize, params.key, "sim")
            .observeOn(Schedulers.io())
            .doOnSuccess {
                // set network value to loading.
                networkState.postValue(NetworkState.LOADING)
            }.subscribeBy(onSuccess = { result ->
                callback.onResult(result.items, params.key + result.items.size)
                networkState.postValue(NetworkState.LOADED)
            }, onError = { e ->
                networkState.postValue(NetworkState.error(e.message ?: "unknown error"))
                e.printStackTrace()
            }).addTo(disposables)
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, ShopItem>) {
        // nothing to do
    }
}