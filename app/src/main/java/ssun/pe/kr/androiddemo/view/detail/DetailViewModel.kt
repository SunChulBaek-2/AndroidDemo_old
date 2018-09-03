package ssun.pe.kr.androiddemo.view.detail

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class DetailViewModel : ViewModel() {

    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val url: MutableLiveData<String> = MutableLiveData()
}