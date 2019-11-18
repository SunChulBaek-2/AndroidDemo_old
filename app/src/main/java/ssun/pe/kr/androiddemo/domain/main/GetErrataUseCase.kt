package ssun.pe.kr.androiddemo.domain.main

import io.reactivex.Single
import ssun.pe.kr.androiddemo.data.main.DefaultSearchRepository
import ssun.pe.kr.androiddemo.domain.SingleUseCase
import ssun.pe.kr.androiddemo.model.ErrataResult

class GetErrataUseCase : SingleUseCase<String, ErrataResult>() {

    private val repository: SearchRepository = DefaultSearchRepository()

    override fun execute(p: String): Single<ErrataResult> = repository.errata(p)
//    {
//        val result = MutableLiveData<ErrataResult>()
//        val networkState = MutableLiveData<NetworkState>()
//        scope.launch {
//            networkState.postValue(NetworkState.LOADING)
//            try {
//                result.postValue(repository.errata(p))
//                networkState.postValue(NetworkState.LOADED)
//            } catch (e: HttpException) {
//                networkState.postValue(NetworkState.error("error code: ${e.code()}"))
//                e.printStackTrace()
//            } catch (e: Exception) {
//                networkState.postValue(NetworkState.error(e.message ?: "unknown error"))
//                e.printStackTrace()
//            }
//        }
//        return Once(
//            result = result,
//            networkState = networkState
//        )
//    }
}