package ssun.pe.kr.androiddemo.data.remote

import android.util.Log
import kotlinx.coroutines.experimental.Deferred
import ssun.pe.kr.androiddemo.data.NaverDataSource
import ssun.pe.kr.androiddemo.data.model.Result
import ssun.pe.kr.androiddemo.network.NaverService
import ssun.pe.kr.androiddemo.network.RetrofitCreator

class NaverRemoteDataSource : NaverDataSource {

    companion object {
        private const val TAG = "NaverRemoteDataSource"
    }

    private val mService: NaverService = RetrofitCreator.create().create(NaverService::class.java)

    override fun searchBlog(query: String): Deferred<Result> {
        Log.d(TAG, "[x1210x]${Thread.currentThread()} searchBlog($query)")
        return mService.searchShop(query)
    }
}