package ssun.pe.kr.androiddemo.presentation

import android.os.Build
import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import ssun.pe.kr.androiddemo.model.Item
import ssun.pe.kr.androiddemo.presentation.main.MainAdapter
import java.text.DecimalFormat

@BindingAdapter("goneUnless")
fun goneUnless(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("imageUrl")
fun imageUrl(iv: ImageView, imageUrl: String?) {
    Glide.with(iv)
            .load(imageUrl)
            .apply(RequestOptions().centerCrop())
            .into(iv)
}

@Suppress("DEPRECATION")
@BindingAdapter("lowestPrice")
fun lowestPrice(tv: TextView, value: Int) {
    val df = DecimalFormat("#,###,###")
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        tv.text = Html.fromHtml("최저 <b>${df.format(value)}</b>원", Html.FROM_HTML_MODE_COMPACT)
    } else {
        tv.text = Html.fromHtml("최저 <b>${df.format(value)}</b>원")
    }
}

@BindingAdapter("items")
fun items(rv: RecyclerView, items: PagedList<Item>?) = items?.let {
    (rv.adapter as? MainAdapter)?.submitList(items)
}