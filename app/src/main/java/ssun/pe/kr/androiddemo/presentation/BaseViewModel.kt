package ssun.pe.kr.androiddemo.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

interface EventActions {
    fun openDetail(url: String)
}

open class BaseViewModel : ViewModel(), EventActions {

    private val _openDetail = MutableLiveData<String>()
    val openDetail: LiveData<String>
        get() = _openDetail

    override fun openDetail(url: String) {
        _openDetail.value = url
    }
}