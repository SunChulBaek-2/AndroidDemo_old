package ssun.pe.kr.androiddemo.presentation.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.ViewModelProviders
import ssun.pe.kr.androiddemo.databinding.FragmentDetailBinding
import ssun.pe.kr.androiddemo.presentation.BaseFragment

class DetailFragment : BaseFragment() {

    companion object {
        const val TAG = "DetailFragment"

        private const val ARG_DETAIL_URL = "ARG_DETAIL_URL"

        fun newInstance(url: String): DetailFragment = DetailFragment().apply {
            val args = Bundle()
            args.putString(ARG_DETAIL_URL, url)
            arguments = args
        }
    }

    private lateinit var detailViewModel: DetailViewModel
    private lateinit var binding: FragmentDetailBinding

    private val url
        get() = arguments?.getString(ARG_DETAIL_URL)

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        detailViewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java).apply {
            isLoading.value = true
        }
        binding = FragmentDetailBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@DetailFragment
            viewModel = this@DetailFragment.detailViewModel

            wvDetail.webViewClient = DetailWebViewClient(detailViewModel)
            wvDetail.settings.javaScriptEnabled = true
            wvDetail.loadUrl(url)
        }
        return binding.root
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