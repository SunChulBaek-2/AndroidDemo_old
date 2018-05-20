package ssun.pe.kr.androiddemo.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ssun.pe.kr.androiddemo.BuildConfig

class RetrofitCreator {
    companion object {
        fun create(): Retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.NaverApiUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
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