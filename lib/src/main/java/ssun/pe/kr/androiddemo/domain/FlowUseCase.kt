package ssun.pe.kr.androiddemo.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import ssun.pe.kr.androiddemo.result.Result

abstract class FlowUseCase<in P, R>(private val defaultDispatcher: CoroutineDispatcher) {

    @ExperimentalCoroutinesApi
    operator fun invoke(p: P): Flow<Result<R>> = execute(p)
        .catch { e -> emit(Result.Error(Exception(e))) }
        .flowOn(defaultDispatcher)

    abstract fun execute(p: P): Flow<Result<R>>
}