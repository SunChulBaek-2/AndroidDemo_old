package ssun.pe.kr.androiddemo.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.transaction
import ssun.pe.kr.androiddemo.R
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Timber.d("[x1210x] onCreate()")

        setContentView(R.layout.activity_main)

        supportFragmentManager.transaction {
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
