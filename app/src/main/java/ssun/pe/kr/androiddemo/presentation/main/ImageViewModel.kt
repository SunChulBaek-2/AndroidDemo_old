package ssun.pe.kr.androiddemo.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ssun.pe.kr.androiddemo.domain.main.SearchImageUseCase
import ssun.pe.kr.androiddemo.model.ImageItem
import ssun.pe.kr.androiddemo.presentation.BaseViewModel
import ssun.pe.kr.androiddemo.presentation.Listing
import ssun.pe.kr.androiddemo.result.Result
import ssun.pe.kr.androiddemo.result.data

class ImageViewModel : BaseViewModel() {

    private val searchImageUseCase = SearchImageUseCase(viewModelScope, Dispatchers.Default)

    private val query = MutableLiveData<String>()

    private val result: LiveData<Result<Listing<ImageItem>>> = switchMap(query) { query ->
        liveData {
            emit(searchImageUseCase(query))
        }
    }
    private val listing: LiveData<Listing<ImageItem>> = switchMap(result) { result ->
        liveData {
            result.data?.let { listing ->
                emit(listing)
            }
        }
    }
    val items = switchMap(listing) { it?.pagedList }
    val networkState = switchMap(listing) { it?.networkState }
    val refreshState = switchMap(listing) { it?.refreshState }

    fun refresh() = listing.value?.refresh?.invoke()

    fun search(query: String) = viewModelScope.launch {
        if (query.isNotBlank()) {
            this@ImageViewModel.query.value = query
        }
    }
}