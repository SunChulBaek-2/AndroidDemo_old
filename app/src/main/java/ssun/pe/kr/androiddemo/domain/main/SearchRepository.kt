package ssun.pe.kr.androiddemo.domain.main

import io.reactivex.Single
import ssun.pe.kr.androiddemo.model.ErrataResult
import ssun.pe.kr.androiddemo.model.ImageResult
import ssun.pe.kr.androiddemo.model.ShopResult

interface SearchRepository {

    fun searchShop(query: String, display: Int?, start: Long?, sort: String?): Single<ShopResult>

    fun searchImage(
        query: String,
        display: Int?,
        start: Long?,
        sort: String?,
        filter: String?
    ): Single<ImageResult>

    fun errata(query: String): Single<ErrataResult>
}