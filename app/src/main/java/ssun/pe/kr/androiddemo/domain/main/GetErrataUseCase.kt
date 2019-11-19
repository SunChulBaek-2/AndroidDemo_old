package ssun.pe.kr.androiddemo.domain.main

import io.reactivex.Single
import ssun.pe.kr.androiddemo.data.main.DefaultSearchRepository
import ssun.pe.kr.androiddemo.domain.SingleUseCase
import ssun.pe.kr.androiddemo.model.ErrataResult

class GetErrataUseCase : SingleUseCase<String, ErrataResult>() {

    private val repository: SearchRepository = DefaultSearchRepository()

    override operator fun invoke(p: String): Single<ErrataResult> = repository.errata(p)
}