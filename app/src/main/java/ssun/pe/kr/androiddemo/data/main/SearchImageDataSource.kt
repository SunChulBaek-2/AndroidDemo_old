package ssun.pe.kr.androiddemo.data.main

import androidx.paging.PageKeyedDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import ssun.pe.kr.androiddemo.domain.main.SearchRepository
import ssun.pe.kr.androiddemo.model.ImageItem

class SearchImageDataSource(
    private val disposables: CompositeDisposable,
    private val query: String
) : PageKeyedDataSource<Long, ImageItem>() {

    private val repository: SearchRepository = DefaultSearchRepository()

    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Long, ImageItem>
    ) {
        repository.searchImage(query, params.requestedLoadSize, 1, "sim", "all")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(onSuccess = { result ->
                callback.onResult(result.items, null, 1L + result.items.size)
            }, onError = { e ->
                e.printStackTrace()
            }).addTo(disposables)
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, ImageItem>) {
        repository.searchImage(query, params.requestedLoadSize, params.key, "sim", "all")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(onSuccess = { result ->
                callback.onResult(result.items, params.key + result.items.size)
            }, onError = { e ->
                e.printStackTrace()
            }).addTo(disposables)
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, ImageItem>) {
        // nothing to do
    }
}