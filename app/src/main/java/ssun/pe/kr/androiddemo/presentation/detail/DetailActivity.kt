package ssun.pe.kr.androiddemo.presentation.detail

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import ssun.pe.kr.androiddemo.R
import ssun.pe.kr.androiddemo.databinding.ActivityDetailBinding
import ssun.pe.kr.androiddemo.presentation.BaseActivity

class DetailActivity : BaseActivity<DetailViewModel>() {

    companion object {
        private const val EXTRA_DETAIL_URL = "EXTRA_DETAIL_URL"

        fun starterIntent(context: Context, url: String?): Intent =
            Intent(context, DetailActivity::class.java).apply {
                putExtra(EXTRA_DETAIL_URL, url)
            }
    }

    private val url
        get() = intent?.getStringExtra(EXTRA_DETAIL_URL)

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java).apply {
            isLoading.value = true
        }
        DataBindingUtil.setContentView<ActivityDetailBinding>(this, R.layout.activity_detail)
            .apply {
                lifecycleOwner = this@DetailActivity
                viewModel = this@DetailActivity.viewModel

                wvDetail.webViewClient = DetailWebViewClient(this@DetailActivity.viewModel)
                wvDetail.settings.javaScriptEnabled = true
                wvDetail.loadUrl(url!!)
            }
        setObservers()
    }

    private class DetailWebViewClient(
        private val detailViewModel: DetailViewModel
    ) : WebViewClient() {

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            detailViewModel.isLoading.value = false
        }
    }
}