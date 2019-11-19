package ssun.pe.kr.androiddemo.domain

import kotlinx.coroutines.flow.Flow

abstract class FlowUseCase<in P, R> : UseCase<P, Flow<R>>()