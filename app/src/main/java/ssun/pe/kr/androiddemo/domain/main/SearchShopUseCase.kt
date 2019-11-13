package ssun.pe.kr.androiddemo.domain.main

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import kotlinx.coroutines.CoroutineScope
import ssun.pe.kr.androiddemo.data.main.SearchShopDataSource
import ssun.pe.kr.androiddemo.domain.UseCase
import ssun.pe.kr.androiddemo.model.Item
import java.util.concurrent.Executors

class SearchShopUseCase(
    private val scope: CoroutineScope
) : UseCase<String, LiveData<PagedList<Item>>>() {

    private lateinit var dataSource: SearchShopDataSource

    override suspend fun execute(p: String): LiveData<PagedList<Item>> {
        return LivePagedListBuilder(
            object : DataSource.Factory<Long, Item>() {
                override fun create(): DataSource<Long, Item> {
                    return SearchShopDataSource(scope, p).apply {
                        dataSource = this
                    }
                }
            }, PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(20) // 설정하지 않을 경우 page size * 3
                .setPageSize(20).build()
        )
            .setFetchExecutor(Executors.newFixedThreadPool(5))
            .build()
    }
}