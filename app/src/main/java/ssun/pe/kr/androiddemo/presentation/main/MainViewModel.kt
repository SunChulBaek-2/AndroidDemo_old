package ssun.pe.kr.androiddemo.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import ssun.pe.kr.androiddemo.domain.main.GetErrataUseCase
import ssun.pe.kr.androiddemo.model.ErrataResult
import ssun.pe.kr.androiddemo.presentation.BaseViewModel
import ssun.pe.kr.androiddemo.result.Result
import ssun.pe.kr.androiddemo.result.data
import timber.log.Timber

class MainViewModel : BaseViewModel() {

    private val getErrataUseCase = GetErrataUseCase(Dispatchers.Default)

    val isRefreshing = MutableLiveData<Boolean>()
    val current = MutableLiveData<Int>()
    val input = MutableLiveData<String>()
    val query = MutableLiveData<String>()

    @ExperimentalCoroutinesApi
    private val result: LiveData<Result<ErrataResult>> = switchMap(input) { input ->
        liveData {
            getErrataUseCase(input).collect {
                when (it) {
                    is Result.Success -> {
                        Timber.d("get errata is success ${it.data.errata}")
                        emit(it)
                    }
                    is Result.Error -> {
                        Timber.e("get errata is failed ${it.exception}")
                    }
                    is Result.Loading -> {
                        Timber.d("get errata is loading")
                    }
                }
            }
        }
    }

    @ExperimentalCoroutinesApi
    val errata: LiveData<ErrataResult> = map(result) { it.data }
}