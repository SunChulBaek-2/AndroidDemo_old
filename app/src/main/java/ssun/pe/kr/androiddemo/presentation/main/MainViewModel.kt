package ssun.pe.kr.androiddemo.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import ssun.pe.kr.androiddemo.domain.main.GetErrataUseCase
import ssun.pe.kr.androiddemo.model.ErrataResult
import ssun.pe.kr.androiddemo.presentation.BaseViewModel
import ssun.pe.kr.androiddemo.result.Result

class MainViewModel : BaseViewModel() {

    private val getErrataUseCase = GetErrataUseCase(Dispatchers.Default)

    val isRefreshing = MutableLiveData<Boolean>()
    val current = MutableLiveData<Int>()
    val input = MutableLiveData<String>()
    val query = MutableLiveData<String>()

    @ExperimentalCoroutinesApi
    private val _result: LiveData<Result<ErrataResult>> = switchMap(input) { input ->
        liveData { getErrataUseCase(input).collect { emit(it) } }
    }
    @ExperimentalCoroutinesApi
    val result
        get() = _result
}