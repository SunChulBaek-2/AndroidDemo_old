package ssun.pe.kr.androiddemo.presentation.main

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import kotlinx.coroutines.launch
import ssun.pe.kr.androiddemo.domain.main.SearchShopUseCase
import ssun.pe.kr.androiddemo.model.ShopItem
import ssun.pe.kr.androiddemo.presentation.BaseViewModel

class ShopViewModel : BaseViewModel() {

    private val searchShopUseCase = SearchShopUseCase(viewModelScope)

    private val _items = MediatorLiveData<PagedList<ShopItem>>()
    val items
        get() = _items

    fun refresh() = searchShopUseCase.refresh()

    fun search(query: String) = viewModelScope.launch {
        if (query.isNotBlank()) {
            _items.addSource(searchShopUseCase.execute(query)) { items ->
                _items.value = items
            }
        }
    }
}