package ssun.pe.kr.androiddemo.presentation.main

import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ssun.pe.kr.androiddemo.R
import ssun.pe.kr.androiddemo.databinding.ActivityMainBinding
import ssun.pe.kr.androiddemo.presentation.BaseActivity
import ssun.pe.kr.androiddemo.presentation.detail.DetailActivity

class MainActivity : BaseActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {
            lifecycleOwner = this@MainActivity
            viewModel = this@MainActivity.mainViewModel

            toolbar.inflateMenu(R.menu.menu_main)
            (toolbar.menu.findItem(R.id.search).actionView as SearchView).setOnQueryTextListener(
                object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        mainViewModel.search(query)
                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean = false
                })

            rvItems.layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            rvItems.adapter = MainAdapter(mainViewModel, this@MainActivity)
            val deco = DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL)
            deco.setDrawable(ContextCompat.getDrawable(this@MainActivity, R.drawable.main_deco)!!)
            rvItems.addItemDecoration(deco)
        }
        setObservers()
    }

    private fun setObservers() {
        mainViewModel.navigateToDetail.observe(this, Observer { url ->
            startActivity(DetailActivity.starterIntent(this, url))
        })
    }

    override fun onBackPressed() {
        if (!(binding.toolbar.menu.findItem(R.id.search).actionView as SearchView).isIconified) {
            binding.toolbar.menu.findItem(R.id.search).collapseActionView()
        } else {
            super.onBackPressed()
        }
    }
}