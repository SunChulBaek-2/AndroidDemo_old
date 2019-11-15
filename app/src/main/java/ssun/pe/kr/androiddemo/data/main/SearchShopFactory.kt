package ssun.pe.kr.androiddemo.data.main

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import kotlinx.coroutines.CoroutineScope
import ssun.pe.kr.androiddemo.model.ShopItem

class SearchShopFactory(
    private val scope: CoroutineScope,
    private val query: String
) : DataSource.Factory<Long, ShopItem>() {

    val sourceLiveData = MutableLiveData<SearchShopDataSource>()

    override fun create(): DataSource<Long, ShopItem> {
        return SearchShopDataSource(scope, query).apply {
            sourceLiveData.postValue(this)
        }
    }
}