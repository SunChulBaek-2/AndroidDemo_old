package ssun.pe.kr.androiddemo.data.main

import io.reactivex.Single
import ssun.pe.kr.androiddemo.data.Naver
import ssun.pe.kr.androiddemo.domain.main.SearchRepository
import ssun.pe.kr.androiddemo.model.ErrataResult
import ssun.pe.kr.androiddemo.model.ImageResult
import ssun.pe.kr.androiddemo.model.ShopResult

class DefaultSearchRepository : SearchRepository {

    private val apiService = Naver.apiService

    override fun searchShop(
        query: String,
        display: Int?,
        start: Long?,
        sort: String?
    ): Single<ShopResult> = apiService.searchShop(query, display, start, sort)

    override fun searchImage(
        query: String,
        display: Int?,
        start: Long?,
        sort: String?,
        filter: String?
    ): Single<ImageResult> = apiService.searchImage(query, display, start, sort, filter)

    override fun errata(query: String): Single<ErrataResult> = apiService.errata(query)
}