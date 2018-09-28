package ssun.pe.kr.androiddemo.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ssun.pe.kr.androiddemo.data.NaverRepository
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(
        private val naverRepository: NaverRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(naverRepository) as T
    }
}