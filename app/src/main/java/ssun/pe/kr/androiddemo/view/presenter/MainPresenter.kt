package ssun.pe.kr.androiddemo.view.presenter

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ssun.pe.kr.androiddemo.data.NaverRepository

class MainPresenter(private val mView: MainContract.View) : MainContract.Presenter {
    private val mDisposables = CompositeDisposable()

    init {
        mView.setPresenter(this)
    }

    override fun destroy() {
        mDisposables.clear()
    }

    override fun searchBlog(query: String) {
        mView.showProgress()
        NaverRepository.searchBlog(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    mView.hideProgress()
                    mView.onSerachBlog(it.total)
                }, {
                    mView.hideProgress()
                    mView.onSerachBlog(-1)
                }).apply { mDisposables.add(this) }
    }
}