package ssun.pe.kr.androiddemo.data.remote

import kotlinx.coroutines.experimental.Deferred
import ssun.pe.kr.androiddemo.data.NaverDataSource
import ssun.pe.kr.androiddemo.data.model.Result
import ssun.pe.kr.androiddemo.network.NaverService
import ssun.pe.kr.androiddemo.network.RetrofitCreator

class NaverRemoteDataSource : NaverDataSource {

    private val mService: NaverService = RetrofitCreator.create().create(NaverService::class.java)

    override fun searchShop(query: String): Deferred<Result> = mService.searchShop(query)
}