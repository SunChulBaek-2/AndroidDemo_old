package ssun.pe.kr.androiddemo.view.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.DividerItemDecoration.VERTICAL
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ssun.pe.kr.androiddemo.R
import ssun.pe.kr.androiddemo.databinding.FragmentMainBinding
import ssun.pe.kr.androiddemo.view.detail.DetailActivity

class MainFragment : Fragment() {

    companion object {
        const val TAG = "MainFragment"
    }

    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: FragmentMainBinding
    private lateinit var adapter: MainAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mainViewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel::class.java)
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

        binding.rvItems.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@MainFragment.adapter
            val deco = DividerItemDecoration(context, VERTICAL)
            deco.setDrawable(ContextCompat.getDrawable(context!!, R.drawable.main_deco)!!)
            addItemDecoration(deco)
        }
    }
}