package ssun.pe.kr.androiddemo.presentation.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.commit
import ssun.pe.kr.androiddemo.R
import ssun.pe.kr.androiddemo.presentation.BaseActivity

class DetailActivity : BaseActivity() {

    companion object {
        private const val EXTRA_DETAIL_URL = "EXTRA_DETAIL_URL"

        fun starterIntent(context: Context, url: String?): Intent =
            Intent(context, DetailActivity::class.java).apply {
                putExtra(EXTRA_DETAIL_URL, url)
            }
    }

    private val url
        get() = intent?.getStringExtra(EXTRA_DETAIL_URL)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        url?.let { url ->
            supportFragmentManager.commit {
                add(R.id.detail_container, DetailFragment.newInstance(url), DetailFragment.TAG)
            }
        }
    }
}