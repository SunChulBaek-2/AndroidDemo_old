package ssun.pe.kr.androiddemo.data

import retrofit2.http.GET
import retrofit2.http.Query
import ssun.pe.kr.androiddemo.model.ErrataResult
import ssun.pe.kr.androiddemo.model.ImageResult
import ssun.pe.kr.androiddemo.model.ShopResult

interface NaverService {

    @GET("image")
    suspend fun searchImage(
        @Query("query") query: String,
        @Query("display") display: Int? = null,
        @Query("start") start: Long? = null,
        @Query("sort") sort: String? = null,
        @Query("filter") filter: String? = null
    ): ImageResult

    @GET("shop.json")
    suspend fun searchShop(
        @Query("query") query: String,
        @Query("display") display: Int? = null,
        @Query("start") start: Long? = null,
        @Query("sort") sort: String? = null
    ): ShopResult

    @GET("errata.json")
    suspend fun errata(
        @Query("query") query: String
    ): ErrataResult
}