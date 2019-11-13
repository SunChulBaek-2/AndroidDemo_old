package ssun.pe.kr.androiddemo

import android.app.Application
import timber.log.Timber

@Suppress("unused")
class App: Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}