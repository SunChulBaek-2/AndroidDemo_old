package ssun.pe.kr.androiddemo.presentation.main

import android.os.Bundle
import androidx.fragment.app.commit
import ssun.pe.kr.androiddemo.R
import ssun.pe.kr.androiddemo.presentation.BaseActivity
import timber.log.Timber

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Timber.d("[x1210x] onCreate()")

        setContentView(R.layout.activity_main)

        supportFragmentManager.commit {
            add(R.id.main_container, MainFragment(), MainFragment.TAG)
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        Timber.d("[x1210x] onDestroy()")
    }

    override fun onBackPressed() {
        // TODO : 이부분은 정리가 필요
        if (!(supportFragmentManager.findFragmentByTag(MainFragment.TAG) as MainFragment).onBackPressed()) {
            super.onBackPressed()
        }
    }
}