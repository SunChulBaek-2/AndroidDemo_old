package ssun.pe.kr.androiddemo.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import ssun.pe.kr.androiddemo.databinding.FragmentImageBinding
import ssun.pe.kr.androiddemo.presentation.BaseFragment

class ImageFragment : BaseFragment<ImageViewModel>() {

    companion object {
        private const val ARG_POSITION = "ARG_POSITION"

        fun newInstance(position: Int) = ImageFragment().apply {
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
        viewModel = ViewModelProvider(this).get(ImageViewModel::class.java)
        val binding = FragmentImageBinding.inflate(inflater, container, false).apply {
            viewModel = this@ImageFragment.viewModel
            lifecycleOwner = this@ImageFragment

            rv.adapter = ImageAdapter(this@ImageFragment.viewModel, this@ImageFragment)
            rv.layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
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

        viewModel.refrefhState.observe(viewLifecycleOwner, Observer {
            // TODO
        })

        viewModel.networkState.observe(viewLifecycleOwner, Observer {
            // TODO
        })
    }
}