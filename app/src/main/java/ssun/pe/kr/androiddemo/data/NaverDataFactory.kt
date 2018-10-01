package ssun.pe.kr.androiddemo.data

import androidx.paging.DataSource
import ssun.pe.kr.androiddemo.data.model.Item

class NaverDataFactory (
        private val query: String
) : DataSource.Factory<Long, Item>() {

    override fun create(): DataSource<Long, Item> = NaverDataSource(query)
}