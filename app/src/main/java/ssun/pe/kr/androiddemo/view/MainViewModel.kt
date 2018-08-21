package ssun.pe.kr.androiddemo.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import ssun.pe.kr.androiddemo.data.NaverRepository
import ssun.pe.kr.androiddemo.data.model.Item

class MainViewModel : ViewModel() {

    val inProgress: MutableLiveData<Boolean> = MutableLiveData()
    val items: MutableLiveData<List<Item>> = MutableLiveData()

    fun searchBlog(query: String) = launch(UI) {
        inProgress.value = true

        try {
            val result = NaverRepository.searchShop(query).await()
            items.value = result.items
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            inProgress.value = false
        }
    }
}