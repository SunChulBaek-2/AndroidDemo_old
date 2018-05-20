package ssun.pe.kr.androiddemo

import android.app.Application
import android.content.Context

class App : Application() {

    companion object {
        private var app: Application? = null
        fun getContext(): Context? = app?.applicationContext
    }

    override fun onCreate() {
        super.onCreate()
        app = this
    }
}