package ssun.pe.kr.androiddemo.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ssun.pe.kr.androiddemo.App
import ssun.pe.kr.androiddemo.data.NaverDataFactory
import ssun.pe.kr.androiddemo.data.NaverDataSource
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    fun provideContext(application: App): Context {
        return application.applicationContext
    }
}