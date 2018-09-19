package ssun.pe.kr.androiddemo.view.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import ssun.pe.kr.androiddemo.R

class DetailActivity : AppCompatActivity() {

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
        setContentView(R.layout.activity_detail)

        intent?.getStringExtra(EXTRA_DETAIL_URL)?.let { url ->
            supportFragmentManager.beginTransaction()
                    .add(R.id.detail_container, DetailFragment.newInstance(url), DetailFragment.TAG)
                    .commit()
        }
    }
}