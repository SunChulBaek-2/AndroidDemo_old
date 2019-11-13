package ssun.pe.kr.androiddemo.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import ssun.pe.kr.androiddemo.data.NaverDataFactory
import ssun.pe.kr.androiddemo.model.Item
import ssun.pe.kr.androiddemo.presentation.BaseViewModel
import java.util.concurrent.Executors

class MainViewModel : BaseViewModel(), EventActions {

    // TODO :
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    // 검색
    private val query: MutableLiveData<String> = MutableLiveData()

    private val _items: LiveData<PagedList<Item>> = Transformations.switchMap(query) {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(20) // 설정하지 않을 경우 page size * 3
            .setPageSize(20).build()

        LivePagedListBuilder(NaverDataFactory(viewModelScope, it), config)
            .setFetchExecutor(Executors.newFixedThreadPool(5))
            .build()
    }
    val items: LiveData<PagedList<Item>>
        get() = _items

    /** LiveData for Actions and Events **/
    private val _navigateToDetail = MutableLiveData<String>()
    val navigateToDetail: LiveData<String>
        get() = _navigateToDetail

    fun search(text: String?) {
        query.value = text
    }

    override fun openDetail(url: String) {
        _navigateToDetail.value = url
    }
}