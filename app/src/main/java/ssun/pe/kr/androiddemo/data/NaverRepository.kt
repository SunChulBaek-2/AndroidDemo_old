package ssun.pe.kr.androiddemo.data

import kotlinx.coroutines.experimental.Deferred
import ssun.pe.kr.androiddemo.data.local.NaverLocalDataSource
import ssun.pe.kr.androiddemo.data.model.Result
import ssun.pe.kr.androiddemo.data.remote.NaverRemoteDataSource

object NaverRepository : NaverDataSource {
    private val local: NaverDataSource = NaverLocalDataSource()
    private val remote: NaverDataSource = NaverRemoteDataSource()

    override fun searchShop(
            query: String,
            display: Int?,
            start: Int?,
            sort: String?): Deferred<Result> {
        return remote.searchShop(query, display, start, sort)
    }
}