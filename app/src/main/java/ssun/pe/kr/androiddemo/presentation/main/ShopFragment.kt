package ssun.pe.kr.androiddemo.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ssun.pe.kr.androiddemo.R
import ssun.pe.kr.androiddemo.databinding.FragmentShopBinding
import ssun.pe.kr.androiddemo.presentation.BaseFragment
import timber.log.Timber

class ShopFragment : BaseFragment<ShopViewModel>() {

    companion object {
        private const val ARG_POSITION = "ARG_POSITION"

        fun newInstance(position: Int) = ShopFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_POSITION, position)
            }
        }
    }

    private val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    }

    private val position
        get() = arguments?.getInt(ARG_POSITION)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(ShopViewModel::class.java)
        val binding = FragmentShopBinding.inflate(inflater, container, false).apply {
            viewModel = this@ShopFragment.viewModel
            lifecycleOwner = this@ShopFragment

            rv.adapter = ShopAdapter(this@ShopFragment.viewModel, this@ShopFragment)
            rv.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            val deco = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
            deco.setDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.main_deco)!!)
            rv.addItemDecoration(deco)
        }
        setObservers()
        return binding.root
    }

    override fun setObservers() {
        super.setObservers()

        mainViewModel.query.observe(viewLifecycleOwner, Observer { query ->
            viewModel.search(query)
        })

        mainViewModel.isRefreshing.observe(viewLifecycleOwner, Observer { isRefreshing ->
            if (isRefreshing && mainViewModel.current.value ?: 0 == position) {
                viewModel.refresh()
            }
        })

        viewModel.items.observe(viewLifecycleOwner, Observer {
            mainViewModel.isRefreshing.value = false
        })

        viewModel.refreshState.observe(viewLifecycleOwner, Observer {
            Timber.d("[x1210x] shop refreshState = $it")
        })

        viewModel.networkState.observe(viewLifecycleOwner, Observer {
            Timber.d("[x1210x] shop networkState = $it")
        })
    }
}