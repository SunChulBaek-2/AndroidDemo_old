package ssun.pe.kr.androiddemo.data

import kotlinx.coroutines.experimental.Deferred
import ssun.pe.kr.androiddemo.data.model.Result
import ssun.pe.kr.androiddemo.network.NaverService
import ssun.pe.kr.androiddemo.network.RetrofitCreator

class RemoteNaverDataSource : NaverDataSource {

    private val naverService: NaverService = RetrofitCreator.create().create(NaverService::class.java)

    override fun searchShop(
            query: String,
            display: Int?,
            start: Int?,
            sort: String?): Deferred<Result> {
        return naverService.searchShop(query, display, start, sort)
    }
}