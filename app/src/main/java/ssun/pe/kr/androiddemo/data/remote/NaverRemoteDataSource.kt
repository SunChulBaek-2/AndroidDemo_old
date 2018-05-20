package ssun.pe.kr.androiddemo.data.remote

import io.reactivex.Single
import ssun.pe.kr.androiddemo.data.NaverDataSource
import ssun.pe.kr.androiddemo.data.model.Result
import ssun.pe.kr.androiddemo.network.NaverService
import ssun.pe.kr.androiddemo.network.RetrofitCreator

class NaverRemoteDataSource : NaverDataSource {
    private val mService: NaverService = RetrofitCreator.create().create(NaverService::class.java)

    override fun searchBlog(query: String): Single<Result>
            = mService.searchBlog(query)
}