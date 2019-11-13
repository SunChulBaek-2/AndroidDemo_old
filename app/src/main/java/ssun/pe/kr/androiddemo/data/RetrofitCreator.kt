package ssun.pe.kr.androiddemo.data

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
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
            .client(OkHttpClient.Builder()
                .addInterceptor(object : Interceptor {
                    override fun intercept(chain: Interceptor.Chain): Response {
                        val request = chain.request().newBuilder()
                            .addHeader("X-Naver-Client-Id", BuildConfig.NaverClientId)
                            .addHeader("X-Naver-Client-Secret", BuildConfig.NaverClientSecret)
                            .build()
                        return chain.proceed(request)
                    }
                })
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }).build()
            )
            .build()
    }
}