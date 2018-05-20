package ssun.pe.kr.androiddemo.view

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
import ssun.pe.kr.androiddemo.data.model.Result
import ssun.pe.kr.androiddemo.view.adapter.MainAdapter
import ssun.pe.kr.androiddemo.view.presenter.MainContract

class MainFragment : Fragment(), MainContract.View {

    companion object {
        const val TAG = "MainFragment"
    }

    var mPresenter: MainContract.Presenter? = null
    val mItems: MutableList<Item> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
            = inflater.inflate(R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnSearch.setOnClickListener {
            etSearch.text.toString().let { text ->
                if (text.isNotBlank()) {
                    mPresenter?.searchBlog(text)
                }
            }
        }

        rvResult.layoutManager = LinearLayoutManager(context)
        rvResult.adapter = MainAdapter(mItems)
        val deco = DividerItemDecoration(context, VERTICAL)
        deco.setDrawable(ContextCompat.getDrawable(context!!, R.drawable.main_deco)!!)
        rvResult.addItemDecoration(deco)
    }

    override fun setPresenter(presenter: MainContract.Presenter) {
        mPresenter = presenter
    }

    override fun showProgress() {
        progress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress.visibility = View.GONE
    }

    override fun onSerachBlog(result: Result?) {
        result?.let {
            mItems.clear()
            mItems.addAll(result.items)
            rvResult.adapter.notifyDataSetChanged()
        }
    }
}