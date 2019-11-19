package ssun.pe.kr.androiddemo.domain.main

import androidx.lifecycle.Transformations
import androidx.paging.PagedList
import androidx.paging.toLiveData
import kotlinx.coroutines.CoroutineScope
import ssun.pe.kr.androiddemo.data.main.SearchShopFactory
import ssun.pe.kr.androiddemo.domain.UseCase
import ssun.pe.kr.androiddemo.model.ShopItem
import ssun.pe.kr.androiddemo.presentation.Listing
import java.util.concurrent.Executors

class SearchShopUseCase(
    private val scope: CoroutineScope
) : UseCase<String, Listing<ShopItem>>() {

    override operator fun invoke(p: String): Listing<ShopItem> {
        val sourceFactory = SearchShopFactory(scope, p)
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