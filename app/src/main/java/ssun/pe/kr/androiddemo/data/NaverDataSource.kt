package ssun.pe.kr.androiddemo.data

import io.reactivex.Single
import ssun.pe.kr.androiddemo.data.model.Result

interface NaverDataSource {
    fun searchBlog(query: String): Single<Result>
}