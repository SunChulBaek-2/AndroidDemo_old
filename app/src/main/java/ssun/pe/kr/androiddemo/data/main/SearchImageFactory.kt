package ssun.pe.kr.androiddemo.data.main

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import ssun.pe.kr.androiddemo.model.ImageItem

class SearchImageFactory(
    private val query: String
) : DataSource.Factory<Long, ImageItem>() {

    val sourceLiveData = MutableLiveData<SearchImageDataSource>()

    override fun create(): DataSource<Long, ImageItem> {
        return SearchImageDataSource(query).apply {
            sourceLiveData.postValue(this)
        }
    }
}