package ssun.pe.kr.androiddemo.view.detail

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.fragment_detail.*
import ssun.pe.kr.androiddemo.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        detailViewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        binding = FragmentDetailBinding.inflate(inflater, container, false).apply {
            setLifecycleOwner(this@DetailFragment)
            viewModel = this@DetailFragment.detailViewModel
        }

        arguments?.getString(ARG_DETAIL_URL)?.let { detailUrl ->
            detailViewModel.isLoading.value = true
            detailViewModel.url.value = detailUrl
        }

        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        wvDetail.webViewClient = DetailWebViewClient(detailViewModel)
        wvDetail.settings.javaScriptEnabled = true
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