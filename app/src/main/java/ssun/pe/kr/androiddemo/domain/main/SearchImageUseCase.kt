package ssun.pe.kr.androiddemo.domain.main

import androidx.lifecycle.Transformations
import androidx.paging.PagedList
import androidx.paging.toLiveData
import kotlinx.coroutines.CoroutineScope
import ssun.pe.kr.androiddemo.data.main.SearchImageFactory
import ssun.pe.kr.androiddemo.domain.Listing
import ssun.pe.kr.androiddemo.domain.UseCase
import ssun.pe.kr.androiddemo.model.ImageItem
import java.util.concurrent.Executors

class SearchImageUseCase(
    private val scope: CoroutineScope
) : UseCase<String, Listing<ImageItem>>() {

    override fun execute(p: String): Listing<ImageItem> {
        val sourceFactory = SearchImageFactory(scope, p)
        val livePagedList = sourceFactory.toLiveData(
            config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(20) // 설정하지 않을 경우 page size * 3
                .setPageSize(20).build(),
            fetchExecutor = Executors.newFixedThreadPool(5)
        )
        val refreshState = Transformations.switchMap(sourceFactory.sourceLiveData) {
            it.initialLoad
        }
        return Listing(
            pagedList = livePagedList,
            networkState = Transformations.switchMap(sourceFactory.sourceLiveData) {
                it.networkState
            },
            retry = { },
            refresh = {
                sourceFactory.sourceLiveData.value?.invalidate()
            },
            refreshState = refreshState
        )
    }
}