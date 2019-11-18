package ssun.pe.kr.androiddemo.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import ssun.pe.kr.androiddemo.domain.main.SearchShopUseCase
import ssun.pe.kr.androiddemo.model.ShopItem
import ssun.pe.kr.androiddemo.presentation.BaseViewModel

class ShopViewModel : BaseViewModel() {

    private val disposables = CompositeDisposable()

    private val searchShopUseCase = SearchShopUseCase(disposables)

    private val _items = MutableLiveData<PagedList<ShopItem>>()
    val items
        get() = _items

    fun refresh() = searchShopUseCase.refresh()

    fun search(query: String) = searchShopUseCase.execute(query)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeBy(onNext = { items ->
            _items.value = items
        }, onComplete = {

        }, onError = { e ->

        }).addTo(disposables)
}