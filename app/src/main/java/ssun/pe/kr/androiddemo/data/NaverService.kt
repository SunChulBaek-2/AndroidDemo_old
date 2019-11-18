package ssun.pe.kr.androiddemo.data

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ssun.pe.kr.androiddemo.model.ErrataResult
import ssun.pe.kr.androiddemo.model.ImageResult
import ssun.pe.kr.androiddemo.model.ShopResult

interface NaverService {

    @GET("image")
    fun searchImage(
        @Query("query") query: String,
        @Query("display") display: Int? = null,
        @Query("start") start: Long? = null,
        @Query("sort") sort: String? = null,
        @Query("filter") filter: String? = null
    ): Single<ImageResult>

    @GET("shop.json")
    fun searchShop(
        @Query("query") query: String,
        @Query("display") display: Int? = null,
        @Query("start") start: Long? = null,
        @Query("sort") sort: String? = null
    ): Single<ShopResult>

    @GET("errata.json")
    fun errata(
        @Query("query") query: String
    ): Single<ErrataResult>
}