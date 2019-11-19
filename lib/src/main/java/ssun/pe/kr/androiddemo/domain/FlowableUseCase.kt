package ssun.pe.kr.androiddemo.domain

import io.reactivex.Flowable

abstract class FlowableUseCase<in P, R> : UseCase<P, Flowable<R>>() {

    abstract override operator fun invoke(p: P): Flowable<R>
}