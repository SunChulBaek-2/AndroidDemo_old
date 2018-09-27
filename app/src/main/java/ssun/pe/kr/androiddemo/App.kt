package ssun.pe.kr.androiddemo

import android.app.Application
import org.koin.android.ext.android.startKoin
import ssun.pe.kr.androiddemo.di.appModule
import timber.log.Timber

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        startKoin(this, listOf(appModule))
    }
}