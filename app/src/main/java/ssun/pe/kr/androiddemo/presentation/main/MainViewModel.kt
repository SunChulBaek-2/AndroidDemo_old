package ssun.pe.kr.androiddemo.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import kotlinx.coroutines.launch
import ssun.pe.kr.androiddemo.domain.main.SearchShopUseCase
import ssun.pe.kr.androiddemo.model.Item
import ssun.pe.kr.androiddemo.presentation.BaseViewModel

class MainViewModel : BaseViewModel(), EventActions {

    private val searchShopUseCase = SearchShopUseCase(viewModelScope)

    // TODO :
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    private val _items = MediatorLiveData<PagedList<Item>>()
    val items
        get() = _items

    private val _navigateToDetail = MutableLiveData<String>()
    val navigateToDetail: LiveData<String>
        get() = _navigateToDetail

    fun search(query: String) = viewModelScope.launch {
        _items.addSource(searchShopUseCase.execute(query)) { items ->
            _items.value = items
        }
    }

    override fun openDetail(url: String) {
        _navigateToDetail.value = url
    }
}