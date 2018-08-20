package ssun.pe.kr.androiddemo.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView.VERTICAL
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_main.*
import ssun.pe.kr.androiddemo.R
import ssun.pe.kr.androiddemo.data.model.Item
import java.util.*

class MainFragment : Fragment() {

    companion object {
        const val TAG = "MainFragment"
    }

    private val mViewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    private val mItems: MutableList<Item> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        setListeners()
        updateViews()
    }

    private fun initViews() {
        rvItems.layoutManager = LinearLayoutManager(context)
        rvItems.adapter = MainAdapter()
        val deco = DividerItemDecoration(context, VERTICAL)
        deco.setDrawable(ContextCompat.getDrawable(context!!, R.drawable.main_deco)!!)
        rvItems.addItemDecoration(deco)
    }

    private fun setListeners() {
        btnSearch.setOnClickListener {
            etSearch.text.toString().let { text ->
                if (text.isNotBlank()) {
                    mViewModel.searchBlog(text)
                }
            }
        }
    }

    private fun updateViews() {
        mViewModel.inProgress.observe(this, Observer { inProgress ->
            progress.visibility = if (inProgress!!) View.VISIBLE else View.GONE
        })

        mViewModel.items.observe(this, Observer { items ->
            (rvItems.adapter as MainAdapter).mItems = items as MutableList<Item>
            (rvItems.adapter as MainAdapter).notifyDataSetChanged()
        })
    }
}