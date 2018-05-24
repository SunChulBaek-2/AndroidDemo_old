package ssun.pe.kr.androiddemo.view

import android.databinding.BaseObservable
import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ssun.pe.kr.androiddemo.data.NaverRepository
import ssun.pe.kr.androiddemo.data.model.Item

class MainViewModel(private val fragment: MainFragment) : BaseObservable() {
    private val mDisposables = CompositeDisposable()

    val items: ObservableList<Item> = ObservableArrayList()

    fun destroy() {
        mDisposables.clear()
    }

    fun searchBlog(query: String) {
        fragment.showProgress()
        NaverRepository.searchBlog(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    fragment.hideProgress()
                    items.clear()
                    items.addAll(result.items)
                }, {
                    fragment.hideProgress()
                }).apply { mDisposables.add(this) }
    }
}