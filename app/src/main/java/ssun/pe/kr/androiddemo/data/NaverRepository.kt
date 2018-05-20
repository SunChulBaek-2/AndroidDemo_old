package ssun.pe.kr.androiddemo.data

import io.reactivex.Single
import ssun.pe.kr.androiddemo.data.local.NaverLocalDataSource
import ssun.pe.kr.androiddemo.data.model.Result
import ssun.pe.kr.androiddemo.data.remote.NaverRemoteDataSource

object NaverRepository : NaverDataSource {
    private val local: NaverDataSource = NaverLocalDataSource()
    private val remote: NaverDataSource = NaverRemoteDataSource()

    override fun searchBlog(query: String): Single<Result>
            = remote.searchBlog(query)
}