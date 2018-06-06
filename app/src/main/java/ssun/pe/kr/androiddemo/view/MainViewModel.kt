package ssun.pe.kr.androiddemo.view

import android.databinding.BaseObservable
import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import android.databinding.ObservableList
import android.util.Log
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import ssun.pe.kr.androiddemo.data.NaverRepository
import ssun.pe.kr.androiddemo.data.model.Item

class MainViewModel : BaseObservable() {

    val inProgress: ObservableField<Boolean> = ObservableField(false)
    val items: ObservableList<Item> = ObservableArrayList()

    fun searchBlog(query: String) = launch(UI) {
        inProgress.set(true)

        try {
            val result = NaverRepository.searchBlog(query).await()
            items.clear()
            items.addAll(result.items)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            inProgress.set(false)
        }
    }
}