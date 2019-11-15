package ssun.pe.kr.androiddemo.data.main

import ssun.pe.kr.androiddemo.data.Naver
import ssun.pe.kr.androiddemo.domain.main.SearchRepository
import ssun.pe.kr.androiddemo.model.ErrataResult
import ssun.pe.kr.androiddemo.model.ImageResult
import ssun.pe.kr.androiddemo.model.ShopResult

class DefaultSearchRepository : SearchRepository {

    private val apiService = Naver.apiService

    override suspend fun searchShop(
        query: String,
        display: Int?,
        start: Long?,
        sort: String?
    ): ShopResult = apiService.searchShop(query, display, start, sort)

    override suspend fun searchImage(
        query: String,
        display: Int?,
        start: Long?,
        sort: String?,
        filter: String?
    ): ImageResult = apiService.searchImage(query, display, start, sort, filter)

    override suspend fun errata(query: String): ErrataResult = apiService.errata(query)
}