package ssun.pe.kr.androiddemo.domain.main

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import ssun.pe.kr.androiddemo.data.main.DefaultSearchRepository
import ssun.pe.kr.androiddemo.domain.UseCase
import ssun.pe.kr.androiddemo.model.ErrataResult
import ssun.pe.kr.androiddemo.presentation.NetworkState
import ssun.pe.kr.androiddemo.presentation.Once

class GetErrataUseCase(
    private val scope: CoroutineScope
) : UseCase<String, Once<ErrataResult>>() {

    private val repository: SearchRepository = DefaultSearchRepository()

    override operator fun invoke(p: String): Once<ErrataResult> {
        val result = MutableLiveData<ErrataResult>()
        val networkState = MutableLiveData<NetworkState>()
        scope.launch {
            networkState.postValue(NetworkState.LOADING)
            try {
                result.postValue(repository.errata(p))
                networkState.postValue(NetworkState.LOADED)
            } catch (e: HttpException) {
                networkState.postValue(NetworkState.error("error code: ${e.code()}"))
                e.printStackTrace()
            } catch (e: Exception) {
                networkState.postValue(NetworkState.error(e.message ?: "unknown error"))
                e.printStackTrace()
            }
        }
        return Once(
            result = result,
            networkState = networkState
        )
    }
}