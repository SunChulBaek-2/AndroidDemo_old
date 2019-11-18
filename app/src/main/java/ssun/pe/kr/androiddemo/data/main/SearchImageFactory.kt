package ssun.pe.kr.androiddemo.data.main

import androidx.paging.DataSource
import io.reactivex.disposables.CompositeDisposable
import ssun.pe.kr.androiddemo.model.ImageItem

class SearchImageFactory(
    private val disposables: CompositeDisposable,
    private val query: String
) : DataSource.Factory<Long, ImageItem>() {

    private lateinit var dataSource: SearchImageDataSource

    override fun create(): DataSource<Long, ImageItem> {
        return SearchImageDataSource(disposables, query).apply {
            dataSource = this
        }
    }

    fun refresh() = dataSource.invalidate()
}