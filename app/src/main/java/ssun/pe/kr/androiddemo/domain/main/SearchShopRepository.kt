package ssun.pe.kr.androiddemo.domain.main

import ssun.pe.kr.androiddemo.model.Result

interface SearchShopRepository {

    suspend fun searchShop(query: String, display: Int?, start: Long?, sort: String?): Result
}