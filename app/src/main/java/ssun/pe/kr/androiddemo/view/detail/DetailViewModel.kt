package ssun.pe.kr.androiddemo.view.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class DetailViewModel : ViewModel() {

    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val url: MutableLiveData<String> = MutableLiveData()

    override fun onCleared() {
        super.onCleared()

        Timber.d("[x1210x] onCleared()")
    }
}