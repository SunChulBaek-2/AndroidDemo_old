package ssun.pe.kr.androiddemo.data.local

import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async
import ssun.pe.kr.androiddemo.data.NaverDataSource
import ssun.pe.kr.androiddemo.data.model.Result

class NaverLocalDataSource : NaverDataSource {
    override fun searchShop(
            query: String,
            display: Int?,
            start: Int?,
            sort: String?): Deferred<Result> = TODO()
}