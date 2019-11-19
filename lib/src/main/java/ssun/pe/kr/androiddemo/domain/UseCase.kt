package ssun.pe.kr.androiddemo.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ssun.pe.kr.androiddemo.result.Result

abstract class UseCase<in P, R>(private val coroutineDispatcher: CoroutineDispatcher) {

    suspend operator fun invoke(p: P): Result<R> = try {
        withContext(coroutineDispatcher) {
            Result.Success(execute(p))
        }
    } catch (e: Exception) {
        Result.Error(e)
    }

    protected abstract fun execute(p: P): R
}