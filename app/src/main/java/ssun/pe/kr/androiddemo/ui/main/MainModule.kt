package ssun.pe.kr.androiddemo.ui.main

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainModule {

    @ContributesAndroidInjector
    abstract fun mainFragment(): MainFragment

    @Binds
    abstract fun bindMainViewModelFactory(factory: MainViewModelFactory): ViewModelProvider.Factory
}