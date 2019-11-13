package ssun.pe.kr.androiddemo.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ssun.pe.kr.androiddemo.R
import ssun.pe.kr.androiddemo.databinding.FragmentMainBinding
import ssun.pe.kr.androiddemo.presentation.BaseFragment
import ssun.pe.kr.androiddemo.presentation.detail.DetailActivity

class MainFragment : BaseFragment() {

    companion object {
        const val TAG = "MainFragment"
    }

    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainViewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel::class.java)
        binding = FragmentMainBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@MainFragment
            viewModel = this@MainFragment.mainViewModel

            toolbar.inflateMenu(R.menu.menu_main)
            (toolbar.menu.findItem(R.id.search).actionView as SearchView).setOnQueryTextListener(
                object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        mainViewModel.search(query)
                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean = false
                })

            rvItems.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            rvItems.adapter = MainAdapter(mainViewModel, viewLifecycleOwner)
            val deco = DividerItemDecoration(context, VERTICAL)
            deco.setDrawable(ContextCompat.getDrawable(context!!, R.drawable.main_deco)!!)
            rvItems.addItemDecoration(deco)
        }
        setObservers()

        return binding.root
    }

    private fun setObservers() {
        mainViewModel.navigateToDetail.observe(viewLifecycleOwner, Observer { url ->
            startActivity(DetailActivity.starterIntent(requireContext(), url))
        })
    }

    fun onBackPressed(): Boolean {
        return if (!(binding.toolbar.menu.findItem(R.id.search).actionView as SearchView).isIconified) {
            binding.toolbar.menu.findItem(R.id.search).collapseActionView()
            true
        } else {
            false
        }
    }
}

