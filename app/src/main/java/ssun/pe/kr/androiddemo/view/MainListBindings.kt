package ssun.pe.kr.androiddemo.view

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import ssun.pe.kr.androiddemo.data.model.Item

@BindingAdapter("items")
fun setItems(rv: RecyclerView, items: List<Item>) {
    val adapter = rv.adapter as MainAdapter
    adapter.replaceData(items)
}