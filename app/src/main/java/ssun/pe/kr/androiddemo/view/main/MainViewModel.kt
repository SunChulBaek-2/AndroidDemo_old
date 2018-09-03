package ssun.pe.kr.androiddemo.view.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import ssun.pe.kr.androiddemo.data.NaverRepository
import ssun.pe.kr.androiddemo.data.model.Item

class MainViewModel : ViewModel(), EventActions {

    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val items: MutableLiveData<List<Item>> = MutableLiveData()

    /** LiveData for Actions and Events **/
    private val _navigateToDetail = MutableLiveData<String>()
    val navigateToDetail: LiveData<String>
        get() = _navigateToDetail

    fun searchBlog(query: String) = launch(UI) {
        isLoading.value = true

        try {
            val result = NaverRepository.searchShop(query).await()
            items.value = result.items
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            isLoading.value = false
        }
    }

    override fun openDetail(url: String) {
        _navigateToDetail.value = url
    }
}