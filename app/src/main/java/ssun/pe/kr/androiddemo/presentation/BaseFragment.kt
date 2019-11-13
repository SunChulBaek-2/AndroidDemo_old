package ssun.pe.kr.androiddemo.presentation

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import ssun.pe.kr.androiddemo.presentation.detail.DetailActivity

abstract class BaseFragment<T : BaseViewModel> : Fragment() {

    protected lateinit var viewModel: T

    protected fun setObservers() {
        viewModel.openDetail.observe(this, Observer { url ->
            startActivity(DetailActivity.starterIntent(requireContext(), url))
        })
    }
}