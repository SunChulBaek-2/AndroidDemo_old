package ssun.pe.kr.androiddemo.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {

    private val _openDetail = MutableLiveData<String>()
    val openDetail: LiveData<String>
        get() = _openDetail

    fun openDetail(url: String) {
        _openDetail.value = url
    }
}