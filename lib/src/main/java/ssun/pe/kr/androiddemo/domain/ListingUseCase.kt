package ssun.pe.kr.androiddemo.domain

import ssun.pe.kr.androiddemo.result.Listing

abstract class ListingUseCase<in P, R> {

    operator fun invoke(p: P): Listing<R> = execute(p)

    protected abstract fun execute(p: P): Listing<R>
}