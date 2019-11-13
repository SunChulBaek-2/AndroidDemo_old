package ssun.pe.kr.androiddemo.presentation.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailViewModel : ViewModel() {

    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
}