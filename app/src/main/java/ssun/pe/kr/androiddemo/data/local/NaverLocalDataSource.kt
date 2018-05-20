package ssun.pe.kr.androiddemo.data.local

import io.reactivex.Single
import ssun.pe.kr.androiddemo.data.NaverDataSource
import ssun.pe.kr.androiddemo.data.model.Result

class NaverLocalDataSource : NaverDataSource {
    override fun searchBlog(query: String): Single<Result>
            = Single.create{ emitter -> emitter.onError(UnsupportedOperationException("")) }
}