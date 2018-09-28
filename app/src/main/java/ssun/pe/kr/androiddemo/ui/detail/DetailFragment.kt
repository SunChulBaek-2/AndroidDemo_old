package ssun.pe.kr.androiddemo.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_detail.*
import ssun.pe.kr.androiddemo.databinding.FragmentDetailBinding
import timber.log.Timber
import javax.inject.Inject

class DetailFragment : DaggerFragment() {

    companion object {
        const val TAG = "DetailFragment"

        private const val ARG_DETAIL_URL = "ARG_DETAIL_URL"

        fun newInstance(url: String): DetailFragment = DetailFragment().apply {
            val args = Bundle()
            args.putString(ARG_DETAIL_URL, url)
            arguments = args
        }
    }

    @Inject lateinit var viewModelFactory: DetailViewModelFactory

    private lateinit var detailViewModel: DetailViewModel
    private lateinit var binding: FragmentDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Timber.d("[x1210x] onCreate()")
    }

    override fun onDestroy() {
        super.onDestroy()

        Timber.d("[x1210x] onDestroy()")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        detailViewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailViewModel::class.java)

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