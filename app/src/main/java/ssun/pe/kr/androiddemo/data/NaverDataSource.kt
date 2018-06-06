package ssun.pe.kr.androiddemo.data

import kotlinx.coroutines.experimental.Deferred
import ssun.pe.kr.androiddemo.data.model.Result

interface NaverDataSource {
    fun searchBlog(query: String): Deferred<Result>
}