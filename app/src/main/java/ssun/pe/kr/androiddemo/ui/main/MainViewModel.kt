package ssun.pe.kr.androiddemo.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.Main
import ssun.pe.kr.androiddemo.data.NaverDataFactory
import ssun.pe.kr.androiddemo.data.model.Item
import timber.log.Timber
import java.util.concurrent.Executors
import kotlin.coroutines.experimental.CoroutineContext

class MainViewModel : ViewModel(), EventActions, CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(20)
            .setPageSize(20).build()

    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val refresh: MutableLiveData<Boolean> = MutableLiveData()
    val query: MutableLiveData<String> = MutableLiveData()
    var items: LiveData<PagedList<Item>> = MutableLiveData()

    /** LiveData for Actions and Events **/
    private val _navigateToDetail = MutableLiveData<String>()
    val navigateToDetail: LiveData<String>
        get() = _navigateToDetail

    init {
        val naverDataFactory = NaverDataFactory("")

        items = LivePagedListBuilder(naverDataFactory, config)
                .setFetchExecutor(Executors.newFixedThreadPool(5))
                .build()
    }

    override fun onCleared() {
        super.onCleared()

        Timber.d("[x1210x] onCleared()")

        job.cancel()
    }

    override fun searchShop() {
        query.value?.let { query ->
            val naverDataFactory = NaverDataFactory(query)

            items = LivePagedListBuilder(naverDataFactory, config)
                    .setFetchExecutor(Executors.newFixedThreadPool(5))
                    .build()

            refresh.value = true
        }
    }

    override fun openDetail(url: String) {
        _navigateToDetail.value = url
    }
}