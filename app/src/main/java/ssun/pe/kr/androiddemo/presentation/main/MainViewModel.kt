package ssun.pe.kr.androiddemo.presentation.main

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import kotlinx.coroutines.launch
import ssun.pe.kr.androiddemo.domain.main.SearchShopUseCase
import ssun.pe.kr.androiddemo.model.Item
import ssun.pe.kr.androiddemo.presentation.BaseViewModel

class MainViewModel : BaseViewModel() {

    private val searchShopUseCase = SearchShopUseCase(viewModelScope)

    // TODO :
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    private val _items = MediatorLiveData<PagedList<Item>>()
    val items
        get() = _items

    fun search(query: String) = viewModelScope.launch {
        if (query.isNotBlank()) {
            _items.addSource(searchShopUseCase.execute(query)) { items ->
                _items.value = items
            }
        }
    }
}