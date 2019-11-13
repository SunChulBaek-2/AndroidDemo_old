package ssun.pe.kr.androiddemo.presentation.main

import androidx.lifecycle.MutableLiveData
import ssun.pe.kr.androiddemo.presentation.BaseViewModel

class MainViewModel : BaseViewModel() {

    val isRefreshing = MutableLiveData<Boolean>()
    val current = MutableLiveData<Int>()
    val query = MutableLiveData<String>()
}