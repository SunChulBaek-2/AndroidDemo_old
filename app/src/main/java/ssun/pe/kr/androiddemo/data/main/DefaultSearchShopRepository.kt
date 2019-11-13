package ssun.pe.kr.androiddemo.data.main

import ssun.pe.kr.androiddemo.data.Naver
import ssun.pe.kr.androiddemo.domain.main.SearchShopRepository
import ssun.pe.kr.androiddemo.model.Result

class DefaultSearchShopRepository : SearchShopRepository {

    private val apiService = Naver.apiService

    override suspend fun searchShop(query: String, display: Int?, start: Long?, sort: String?): Result =
        apiService.searchShop(query, display, start, sort)
}