package ssun.pe.kr.androiddemo.data.main

import androidx.paging.DataSource
import io.reactivex.disposables.CompositeDisposable
import ssun.pe.kr.androiddemo.model.ShopItem

class SearchShopFactory(
    private val disposables: CompositeDisposable,
    private val query: String
) : DataSource.Factory<Long, ShopItem>() {

    private lateinit var dataSource: SearchShopDataSource

    override fun create(): DataSource<Long, ShopItem> {
        return SearchShopDataSource(disposables, query).apply {
            dataSource = this
        }
    }

    fun refresh() = dataSource.invalidate()
}