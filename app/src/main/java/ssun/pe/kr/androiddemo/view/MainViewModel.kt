package ssun.pe.kr.androiddemo.view

import android.databinding.BaseObservable
import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import android.databinding.ObservableList
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ssun.pe.kr.androiddemo.data.NaverRepository
import ssun.pe.kr.androiddemo.data.model.Item

class MainViewModel : BaseObservable() {
    private val mDisposables = CompositeDisposable()

    val inProgress: ObservableField<Boolean> = ObservableField(false)
    val items: ObservableList<Item> = ObservableArrayList()

    fun destroy() {
        mDisposables.clear()
    }

    fun searchBlog(query: String) {
        inProgress.set(true)
        NaverRepository.searchBlog(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    inProgress.set(false)
                    items.clear()
                    items.addAll(result.items)
                }, {
                    inProgress.set(false)
                }).apply { mDisposables.add(this) }
    }
}