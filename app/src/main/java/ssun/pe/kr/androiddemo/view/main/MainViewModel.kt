package ssun.pe.kr.androiddemo.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.Main
import ssun.pe.kr.androiddemo.data.NaverRepository
import ssun.pe.kr.androiddemo.data.model.Item
import timber.log.Timber
import kotlin.coroutines.experimental.CoroutineContext

class MainViewModel(
        private val repository: NaverRepository
) : ViewModel(), EventActions, CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val query: MutableLiveData<String> = MutableLiveData()
    val items: MutableLiveData<MutableList<Item>> = MutableLiveData()

    /** LiveData for Actions and Events **/
    private val _navigateToDetail = MutableLiveData<String>()
    val navigateToDetail: LiveData<String>
        get() = _navigateToDetail

    override fun onCleared() {
        super.onCleared()

        Timber.d("[x1210x] onCleared()")

        job.cancel()
    }

    fun searchShop(query: String, start: Int? = 1) = launch {
        isLoading.value = true

        try {
            val result = repository.searchShop(query = query, start = start).await()
            if (start == 1) {
                items.value = result.items as MutableList<Item>
            } else {
                items.value?.addAll(result.items)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            isLoading.value = false
        }
    }

    override fun loadMore() {
        searchShop(query.value!!, items.value!!.size + 1)
    }

    override fun openDetail(url: String) {
        _navigateToDetail.value = url
    }
}