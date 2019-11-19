package ssun.pe.kr.androiddemo.presentation.main

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import ssun.pe.kr.androiddemo.R
import ssun.pe.kr.androiddemo.databinding.ActivityMainBinding
import ssun.pe.kr.androiddemo.presentation.BaseActivity
import ssun.pe.kr.androiddemo.result.Result
import timber.log.Timber

class MainActivity : BaseActivity<MainViewModel>() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
            .apply {
                lifecycleOwner = this@MainActivity
                viewModel = this@MainActivity.viewModel

                toolbar.inflateMenu(R.menu.menu_main)
                (toolbar.menu.findItem(R.id.search).actionView as SearchView).setOnQueryTextListener(
                    object : SearchView.OnQueryTextListener {
                        override fun onQueryTextSubmit(query: String?): Boolean = query?.let {
                            this@MainActivity.viewModel.query.value = query
                            hideKeyboard(binding.root.findFocus())
                            true
                        } ?: false

                        override fun onQueryTextChange(newText: String?): Boolean {
                            this@MainActivity.viewModel.input.value = newText
                            return false
                        }
                    })

                vp.adapter = MainAdapter(supportFragmentManager)
                // https://stackoverflow.com/questions/25978462/swiperefreshlayout-viewpager-limit-horizontal-scroll-only
                vp.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) { }

                    override fun onPageSelected(position: Int) { }

                    override fun onPageScrollStateChanged(state: Int) {
                        swipe.isEnabled = state == ViewPager.SCROLL_STATE_IDLE
                    }
                })
                tab.setupWithViewPager(vp)
            }
        setObservers()
    }

    override fun setObservers() {
        super.setObservers()

        viewModel.result.observe(this, Observer {
            when (it) {
                is Result.Success -> {
                    Timber.d("[x1210x] get errata is success ${it.data.errata}")
                }
                is Result.Error -> {
                    Timber.e("[x1210x] get errata is failed ${it.exception}")
                }
                is Result.Loading -> {
                    Timber.d("[x1210x] get errata is loading")
                }
            }
        })
    }

    override fun onBackPressed() {
        if (!(binding.toolbar.menu.findItem(R.id.search).actionView as SearchView).isIconified) {
            binding.toolbar.menu.findItem(R.id.search).collapseActionView()
        } else {
            super.onBackPressed()
        }
    }

    private fun hideKeyboard(v: View) {
        val imm = (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
        imm.hideSoftInputFromWindow(v.windowToken, 0)
    }
}