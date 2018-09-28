package ssun.pe.kr.androiddemo.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ssun.pe.kr.androiddemo.App
import ssun.pe.kr.androiddemo.data.LocalNaverDataSource
import ssun.pe.kr.androiddemo.data.NaverDataSource
import ssun.pe.kr.androiddemo.data.NaverRepository
import ssun.pe.kr.androiddemo.data.RemoteNaverDataSource
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    fun provideContext(application: App): Context {
        return application.applicationContext
    }

    @Provides
    @Named("local")
    fun provideLocalNaverDataSource(): NaverDataSource {
        return LocalNaverDataSource()
    }

    @Provides
    @Named("remote")
    fun provideRemoteNaverDataSource(): NaverDataSource {
        return RemoteNaverDataSource()
    }

    @Singleton
    @Provides
    fun provideNaverRepository(
            @Named("local") localNaverDataSource: NaverDataSource,
            @Named("remote") remoteNaverDataSource: NaverDataSource
    ): NaverRepository {
        return NaverRepository(localNaverDataSource, remoteNaverDataSource)
    }
}