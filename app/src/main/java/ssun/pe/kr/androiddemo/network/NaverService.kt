package ssun.pe.kr.androiddemo.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import ssun.pe.kr.androiddemo.BuildConfig
import ssun.pe.kr.androiddemo.data.model.Result

interface NaverService {
    @GET("blog")
    @Headers(
            "X-Naver-Client-Id: ${BuildConfig.NaverClientId}",
            "X-Naver-Client-Secret: ${BuildConfig.NaverClientSecret}")
    fun searchBlog(@Query("query") query: String): Single<Result>
}