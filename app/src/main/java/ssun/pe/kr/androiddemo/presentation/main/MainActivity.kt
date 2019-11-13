package ssun.pe.kr.androiddemo.presentation.main

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ssun.pe.kr.androiddemo.R
import ssun.pe.kr.androiddemo.databinding.ActivityMainBinding
import ssun.pe.kr.androiddemo.presentation.BaseActivity

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
                            this@MainActivity.viewModel.search(query)
                            hideKeyboard(binding.root.findFocus())
                            true
                        } ?: false

                        override fun onQueryTextChange(newText: String?): Boolean = false
                    })

                rvItems.layoutManager =
                    LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
                rvItems.adapter = MainAdapter(this@MainActivity.viewModel, this@MainActivity)
                val deco = DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL)
                deco.setDrawable(
                    ContextCompat.getDrawable(
                        this@MainActivity,
                        R.drawable.main_deco
                    )!!
                )
                rvItems.addItemDecoration(deco)
            }
        setObservers()
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