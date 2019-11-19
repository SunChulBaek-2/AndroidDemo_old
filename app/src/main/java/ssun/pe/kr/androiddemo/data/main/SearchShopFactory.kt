package ssun.pe.kr.androiddemo.data.main

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import ssun.pe.kr.androiddemo.model.ShopItem

class SearchShopFactory(
    private val query: String
) : DataSource.Factory<Long, ShopItem>() {

    val sourceLiveData = MutableLiveData<SearchShopDataSource>()

    override fun create(): DataSource<Long, ShopItem> {
        return SearchShopDataSource(query).apply {
            sourceLiveData.postValue(this)
        }
    }
}