package ssun.pe.kr.androiddemo.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ssun.pe.kr.androiddemo.BuildConfig

class RetrofitCreator {
    companion object {
        fun create(): Retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.NaverApiUrl)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .client(loggingClient())
                .build()

        private fun loggingClient(): OkHttpClient {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            return OkHttpClient.Builder().addInterceptor(interceptor).build()
        }
    }
}