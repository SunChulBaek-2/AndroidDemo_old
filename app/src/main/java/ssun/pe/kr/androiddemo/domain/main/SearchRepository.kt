package ssun.pe.kr.androiddemo.domain.main

import ssun.pe.kr.androiddemo.model.ImageResult
import ssun.pe.kr.androiddemo.model.ShopResult

interface SearchRepository {

    suspend fun searchShop(query: String, display: Int?, start: Long?, sort: String?): ShopResult

    suspend fun searchImage(
        query: String,
        display: Int?,
        start: Long?,
        sort: String?,
        filter: String?
    ): ImageResult
}