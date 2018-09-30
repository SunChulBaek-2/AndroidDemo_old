package ssun.pe.kr.androiddemo.ui.main

interface EventActions {
    fun openDetail(url: String)
    fun removeItem(productId: Long): Boolean
    fun loadMore()
}