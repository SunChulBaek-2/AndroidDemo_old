package ssun.pe.kr.androiddemo.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.viewModelScope
import ssun.pe.kr.androiddemo.domain.main.GetErrataUseCase
import ssun.pe.kr.androiddemo.model.ErrataResult
import ssun.pe.kr.androiddemo.presentation.BaseViewModel
import ssun.pe.kr.androiddemo.presentation.NetworkState
import ssun.pe.kr.androiddemo.presentation.Once

class MainViewModel : BaseViewModel() {

    private val getErrataUseCase = GetErrataUseCase(viewModelScope)

    val isRefreshing = MutableLiveData<Boolean>()
    val current = MutableLiveData<Int>()
    val input = MutableLiveData<String>()
    val query = MutableLiveData<String>()

    private val result: LiveData<Once<ErrataResult>> = map(input) { getErrataUseCase.execute(it) }
    val errata: LiveData<ErrataResult> = switchMap(result) { it.result }
    val networkState: LiveData<NetworkState> = switchMap(result) { it.networkState }
}