package ssun.pe.kr.androiddemo.presentation.detail

import androidx.lifecycle.MutableLiveData
import ssun.pe.kr.androiddemo.presentation.BaseViewModel

class DetailViewModel : BaseViewModel() {

    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
}