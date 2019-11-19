package ssun.pe.kr.androiddemo.domain.main

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ssun.pe.kr.androiddemo.data.main.DefaultSearchRepository
import ssun.pe.kr.androiddemo.domain.FlowUseCase
import ssun.pe.kr.androiddemo.model.ErrataResult
import ssun.pe.kr.androiddemo.result.Result

class GetErrataUseCase(
    coroutineDispatcher: CoroutineDispatcher
) : FlowUseCase<String, ErrataResult>(coroutineDispatcher) {

    private val repository: SearchRepository = DefaultSearchRepository()

    override fun execute(p: String): Flow<Result<ErrataResult>> = flow {
        emit(Result.Success(repository.errata(p)))
    }
}