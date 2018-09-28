package ssun.pe.kr.androiddemo.ui.main

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import ssun.pe.kr.androiddemo.R
import timber.log.Timber

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Timber.d("[x1210x] onCreate()")

        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
                .add(R.id.main_container, MainFragment(), MainFragment.TAG)
                .commit()
    }

    override fun onDestroy() {
        super.onDestroy()

        Timber.d("[x1210x] onDestroy()")
    }
}
