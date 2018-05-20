package ssun.pe.kr.androiddemo.view.presenter

interface MainContract {
    interface View {
        fun setPresenter(presenter: Presenter)
        fun showProgress()
        fun hideProgress()
        fun onSerachBlog(total: Int?)
    }

    interface Presenter {
        fun destroy()
        fun searchBlog(query: String)
    }
}