package ssun.pe.kr.androiddemo.presentation

import androidx.lifecycle.LiveData

data class Once<T>(
    val result: LiveData<T>,
    val networkState: LiveData<NetworkState>
)