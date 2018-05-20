package ssun.pe.kr.androiddemo.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_main.*
import ssun.pe.kr.androiddemo.R
import ssun.pe.kr.androiddemo.view.presenter.MainContract

class MainFragment : Fragment(), MainContract.View {

    companion object {
        const val TAG = "MainFragment"
    }

    var mPresenter: MainContract.Presenter? = null

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

    override fun onSerachBlog(total: Int?) {
        tvTotal.text = total?.toString()
    }
}