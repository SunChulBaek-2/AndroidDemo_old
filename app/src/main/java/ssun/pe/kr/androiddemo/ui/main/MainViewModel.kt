package ssun.pe.kr.androiddemo.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import kotlinx.coroutines.experimental.*
import ssun.pe.kr.androiddemo.data.NaverDataFactory
import ssun.pe.kr.androiddemo.data.model.Item
import timber.log.Timber
import java.util.concurrent.Executors
import kotlin.coroutines.experimental.CoroutineContext

class MainViewModel : ViewModel(), EventActions, CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    // TODO :
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    // 검색
    private val query: MutableLiveData<String> = MutableLiveData()

    private val _items: LiveData<PagedList<Item>> = Transformations.switchMap(query) {
        val config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(20) // 설정하지 않을 경우 page size * 3
                .setPageSize(20).build()

        LivePagedListBuilder(NaverDataFactory(it), config)
                .setFetchExecutor(Executors.newFixedThreadPool(5))
                .build()
    }
    val items: LiveData<PagedList<Item>>
        get() = _items

    /** LiveData for Actions and Events **/
    private val _navigateToDetail = MutableLiveData<String>()
    val navigateToDetail: LiveData<String>
        get() = _navigateToDetail

    override fun onCleared() {
        super.onCleared()

        Timber.d("[x1210x] onCleared()")

        job.cancel()
    }

    fun search(text: String?) {
        query.value = text
    }

    override fun openDetail(url: String) {
        _navigateToDetail.value = url
    }
}