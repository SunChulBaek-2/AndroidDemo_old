package ssun.pe.kr.androiddemo.domain.main

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import kotlinx.coroutines.CoroutineScope
import ssun.pe.kr.androiddemo.data.main.SearchImageDataSource
import ssun.pe.kr.androiddemo.domain.UseCase
import ssun.pe.kr.androiddemo.model.ImageItem
import java.util.concurrent.Executors

class SearchImageUseCase(
    private val scope: CoroutineScope
) : UseCase<String, LiveData<PagedList<ImageItem>>>() {

    private lateinit var dataSource: SearchImageDataSource

    override suspend fun execute(p: String): LiveData<PagedList<ImageItem>> {
        return LivePagedListBuilder(
            object : DataSource.Factory<Long, ImageItem>() {
                override fun create(): DataSource<Long, ImageItem> {
                    return SearchImageDataSource(scope, p).apply {
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

    fun refresh() = dataSource.invalidate()
}