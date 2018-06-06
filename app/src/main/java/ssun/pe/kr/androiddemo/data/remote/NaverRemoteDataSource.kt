package ssun.pe.kr.androiddemo.data.remote

import android.util.Log
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async
import ssun.pe.kr.androiddemo.data.NaverDataSource
import ssun.pe.kr.androiddemo.data.model.Result
import ssun.pe.kr.androiddemo.network.NaverService
import ssun.pe.kr.androiddemo.network.RetrofitCreator

class NaverRemoteDataSource : NaverDataSource {

    companion object {
        private const val TAG = "NaverRemoteDataSource"
    }

    private val mService: NaverService = RetrofitCreator.create().create(NaverService::class.java)

    override suspend fun searchBlog(query: String): Deferred<Result> = async(CommonPool) {
        Log.d(TAG, "[x1210x](${Thread.currentThread().name}) searchBlog($query)")
        mService.searchShop(query)
    }.await()
}