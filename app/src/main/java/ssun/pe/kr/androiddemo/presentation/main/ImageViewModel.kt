package ssun.pe.kr.androiddemo.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ssun.pe.kr.androiddemo.domain.main.SearchImageUseCase
import ssun.pe.kr.androiddemo.model.ImageItem
import ssun.pe.kr.androiddemo.presentation.BaseViewModel
import ssun.pe.kr.androiddemo.result.Listing

class ImageViewModel : BaseViewModel() {

    private val searchImageUseCase = SearchImageUseCase()

    private val query = MutableLiveData<String>()

    private val result: LiveData<Listing<ImageItem>> = map(query) { query ->
        searchImageUseCase(query)
    }
    val items = switchMap(result) { it?.pagedList }
    val networkState = switchMap(result) { it?.networkState }
    val refreshState = switchMap(result) { it?.refreshState }

    override fun onCleared() {
        super.onCleared()
        result.value?.clearCoroutineJobs?.invoke()
    }

    fun refresh() = result.value?.refresh?.invoke()

    fun search(query: String) = viewModelScope.launch {
        if (query.isNotBlank()) {
            this@ImageViewModel.query.value = query
        }
    }
}