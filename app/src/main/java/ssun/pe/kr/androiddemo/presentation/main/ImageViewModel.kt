package ssun.pe.kr.androiddemo.presentation.main

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import kotlinx.coroutines.launch
import ssun.pe.kr.androiddemo.domain.main.SearchImageUseCase
import ssun.pe.kr.androiddemo.model.ImageItem
import ssun.pe.kr.androiddemo.presentation.BaseViewModel

class ImageViewModel : BaseViewModel() {

    private val searchImageUseCase = SearchImageUseCase(viewModelScope)

    private val _items = MediatorLiveData<PagedList<ImageItem>>()
    val items
        get() = _items

    fun refresh() = searchImageUseCase.refresh()

    fun search(query: String) = viewModelScope.launch {
        if (query.isNotBlank()) {
            _items.addSource(searchImageUseCase.execute(query)) { items ->
                _items.value = items
            }
        }
    }
}