package ssun.pe.kr.androiddemo.data

import retrofit2.http.GET
import retrofit2.http.Query
import ssun.pe.kr.androiddemo.data.model.Result

interface NaverService {
    @GET("shop.json")
    suspend fun searchShop(
        @Query("query") query: String,
        @Query("display") display: Int?,
        @Query("start") start: Long?,
        @Query("sort") sort: String?
    ): Result
}