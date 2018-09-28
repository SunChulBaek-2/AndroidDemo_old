package ssun.pe.kr.androiddemo.view.detail

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DetailModule {

    @ContributesAndroidInjector
    abstract fun detailFragment(): DetailFragment

    @Binds
    abstract fun bindDetailViewModelFactory(factory: DetailViewModelFactory): ViewModelProvider.Factory
}