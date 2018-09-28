package ssun.pe.kr.androiddemo.data

import kotlinx.coroutines.experimental.Deferred
import ssun.pe.kr.androiddemo.data.model.Result
import javax.inject.Inject

class NaverRepository @Inject constructor(
        private val localNaverDataSource: NaverDataSource,
        private val remoteNaverDataSource: NaverDataSource
) {

    fun searchShop(
            query: String,
            display: Int? = 10,
            start: Int? = 1,
            sort: String? = "sim"): Deferred<Result> {
        return remoteNaverDataSource.searchShop(query, display, start, sort)
    }
}