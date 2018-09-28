package ssun.pe.kr.androiddemo.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ssun.pe.kr.androiddemo.ui.detail.DetailActivity
import ssun.pe.kr.androiddemo.ui.detail.DetailModule
import ssun.pe.kr.androiddemo.ui.main.MainActivity
import ssun.pe.kr.androiddemo.ui.main.MainModule

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun mainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [DetailModule::class])
    abstract fun detailActivity() : DetailActivity
}