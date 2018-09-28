package ssun.pe.kr.androiddemo

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import ssun.pe.kr.androiddemo.di.DaggerAppComponent
import timber.log.Timber

class App: DaggerApplication() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }
}