package ssun.pe.kr.androiddemo

import android.app.Application
import org.koin.android.ext.android.startKoin
import ssun.pe.kr.androiddemo.di.appModule

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(appModule))
    }
}