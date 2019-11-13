package ssun.pe.kr.androiddemo.data

import androidx.paging.DataSource
import kotlinx.coroutines.CoroutineScope
import ssun.pe.kr.androiddemo.model.Item

class NaverDataFactory (
    private val scope: CoroutineScope,
    private val query: String
) : DataSource.Factory<Long, Item>() {

    override fun create(): DataSource<Long, Item> = NaverDataSource(scope, query)
}