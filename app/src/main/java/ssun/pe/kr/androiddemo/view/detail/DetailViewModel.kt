package ssun.pe.kr.androiddemo.view.detail

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import timber.log.Timber

class DetailViewModel : ViewModel() {

    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val url: MutableLiveData<String> = MutableLiveData()

    override fun onCleared() {
        super.onCleared()

        Timber.d("[x1210x] onCleared()")
    }
}