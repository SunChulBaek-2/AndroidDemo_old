package ssun.pe.kr.androiddemo.presentation.main

import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import ssun.pe.kr.androiddemo.domain.main.GetErrataUseCase
import ssun.pe.kr.androiddemo.model.ErrataResult
import ssun.pe.kr.androiddemo.presentation.BaseViewModel
import ssun.pe.kr.androiddemo.presentation.NetworkState

class MainViewModel : BaseViewModel() {

    private val disposables = CompositeDisposable()

    private val getErrataUseCase = GetErrataUseCase()

    val isRefreshing = MutableLiveData<Boolean>()
    val current = MutableLiveData<Int>()
    val query = MutableLiveData<String>()

    val errata = MutableLiveData<ErrataResult>()
    val networkState = MutableLiveData<NetworkState>()

    fun getErrata(input: String?) = getErrataUseCase(input ?: "")
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeBy(onSuccess = {
            errata.value = it
        }, onError = {

        }).addTo(disposables)
}