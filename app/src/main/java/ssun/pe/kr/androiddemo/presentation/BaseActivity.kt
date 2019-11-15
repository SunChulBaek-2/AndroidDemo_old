package ssun.pe.kr.androiddemo.presentation

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import ssun.pe.kr.androiddemo.presentation.detail.DetailActivity

abstract class BaseActivity<T : BaseViewModel> : AppCompatActivity() {

    protected lateinit var viewModel: T

    protected open fun setObservers() {
        viewModel.openDetail.observe(this, Observer { url ->
            startActivity(DetailActivity.starterIntent(this, url))
        })
    }
}