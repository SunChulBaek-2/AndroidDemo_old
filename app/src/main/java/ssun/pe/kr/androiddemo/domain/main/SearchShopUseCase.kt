package ssun.pe.kr.androiddemo.domain.main

import androidx.lifecycle.Transformations
import androidx.paging.PagedList
import androidx.paging.toLiveData
import ssun.pe.kr.androiddemo.data.main.SearchShopFactory
import ssun.pe.kr.androiddemo.domain.ListingUseCase
import ssun.pe.kr.androiddemo.model.ShopItem
import ssun.pe.kr.androiddemo.result.Listing
import java.util.concurrent.Executors

class SearchShopUseCase : ListingUseCase<String, ShopItem>() {

    override fun execute(p: String): Listing<ShopItem> {
        val sourceFactory = SearchShopFactory(p)
        val livePagedList = sourceFactory.toLiveData(
            config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(20) // 설정하지 않을 경우 page size * 3
                .setPageSize(20).build(),
            fetchExecutor = Executors.newFixedThreadPool(5)
        )
        return Listing(
            pagedList = livePagedList,
            networkState = Transformations.switchMap(sourceFactory.sourceLiveData) {
                it.networkState
            },
            retry = { },
            refresh = {
                sourceFactory.sourceLiveData.value?.invalidate()
            },
            refreshState = Transformations.switchMap(sourceFactory.sourceLiveData) {
                it.initialLoad
            },
            clearCoroutineJobs = {
                sourceFactory.sourceLiveData.value?.clearCoroutineJobs()
            }
        )
    }
}