package ssun.pe.kr.androiddemo.data

import kotlinx.coroutines.experimental.Deferred
import ssun.pe.kr.androiddemo.data.model.Result

interface NaverDataSource {
    suspend fun searchBlog(query: String): Deferred<Result>
}