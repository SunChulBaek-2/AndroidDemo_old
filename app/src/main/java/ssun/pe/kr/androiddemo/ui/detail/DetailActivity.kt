package ssun.pe.kr.androiddemo.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import ssun.pe.kr.androiddemo.R
import timber.log.Timber

class DetailActivity : DaggerAppCompatActivity() {

    companion object {
        private const val EXTRA_DETAIL_URL = "EXTRA_DETAIL_URL"

        fun starterIntent(context: Context, url: String?): Intent {
            return Intent(context, DetailActivity::class.java).apply {
                putExtra(EXTRA_DETAIL_URL, url)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Timber.d("[x1210x] onCreate()")

        setContentView(R.layout.activity_detail)

        intent?.getStringExtra(EXTRA_DETAIL_URL)?.let { url ->
            supportFragmentManager.beginTransaction()
                    .add(R.id.detail_container, DetailFragment.newInstance(url), DetailFragment.TAG)
                    .commit()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        Timber.d("[x1210x] onDestroy()")
    }
}