package ssun.pe.kr.androiddemo.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import ssun.pe.kr.androiddemo.domain.main.SearchImageUseCase
import ssun.pe.kr.androiddemo.model.ImageItem
import ssun.pe.kr.androiddemo.presentation.BaseViewModel

class ImageViewModel : BaseViewModel() {

    private val disposables = CompositeDisposable()

    private val searchImageUseCase = SearchImageUseCase(disposables)

    private val _items = MutableLiveData<PagedList<ImageItem>>()
    val items
        get() = _items

    fun refresh() = searchImageUseCase.refresh()

    fun search(query: String) = searchImageUseCase(query)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeBy(onNext = { items ->
            _items.value = items
        }, onComplete = {

        }, onError = { e ->
            e.printStackTrace()
        }).addTo(disposables)
}