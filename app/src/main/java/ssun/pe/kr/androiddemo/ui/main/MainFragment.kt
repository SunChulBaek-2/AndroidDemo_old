package ssun.pe.kr.androiddemo.ui.main

import androidx.lifecycle.Observer
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerFragment
import ssun.pe.kr.androiddemo.R
import ssun.pe.kr.androiddemo.databinding.FragmentMainBinding
import ssun.pe.kr.androiddemo.ui.detail.DetailActivity
import timber.log.Timber
import javax.inject.Inject

class MainFragment : DaggerFragment() {

    companion object {
        const val TAG = "MainFragment"
    }

    @Inject lateinit var viewModelFactory: MainViewModelFactory

    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: FragmentMainBinding
    private lateinit var adapter: MainAdapter

    private var isSearchViewOpen = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Timber.d("[x1210x] onCreate()")
    }

    override fun onDestroy() {
        super.onDestroy()

        Timber.d("[x1210x] onDestroy()")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mainViewModel = ViewModelProviders.of(requireActivity(), viewModelFactory).get(MainViewModel::class.java)

        binding = FragmentMainBinding.inflate(inflater, container, false).apply {
            setLifecycleOwner(this@MainFragment)
            viewModel = this@MainFragment.mainViewModel
        }

        mainViewModel.navigateToDetail.observe(this, Observer { url ->
            startActivity(DetailActivity.starterIntent(requireContext(), url))
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()

        mainViewModel.items.observe(this, Observer { items ->
            adapter.submitList(items)
        })
    }

    private fun initViews() {
        adapter = MainAdapter(mainViewModel, this)

        binding.toolbar.inflateMenu(R.menu.menu_main)

        binding.toolbar.menu.findItem(R.id.search).setOnActionExpandListener(object: MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                isSearchViewOpen = true
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                isSearchViewOpen = false
                return true
            }
        })

        (binding.toolbar.menu.findItem(R.id.search).actionView as SearchView).setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                mainViewModel.search(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean = false
        })

        binding.rvItems.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@MainFragment.adapter
            val deco = DividerItemDecoration(context, VERTICAL)
            deco.setDrawable(ContextCompat.getDrawable(context!!, R.drawable.main_deco)!!)
            addItemDecoration(deco)
        }
    }

    fun onBackPressed(): Boolean {
        return if (isSearchViewOpen) {
            binding.toolbar.menu.findItem(R.id.search).collapseActionView()
            true
        } else {
            false
        }
    }
}