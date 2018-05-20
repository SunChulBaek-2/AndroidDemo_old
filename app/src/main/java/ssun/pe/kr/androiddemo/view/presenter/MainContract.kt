package ssun.pe.kr.androiddemo.view.presenter

import ssun.pe.kr.androiddemo.data.model.Result

interface MainContract {
    interface View {
        fun setPresenter(presenter: Presenter)
        fun showProgress()
        fun hideProgress()
        fun onSerachBlog(result: Result?)
    }

    interface Presenter {
        fun destroy()
        fun searchBlog(query: String)
    }
}