package ssun.pe.kr.androiddemo.util

import android.databinding.BindingAdapter
import android.text.Html
import android.view.View
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
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

@BindingAdapter("webViewUrl")
fun webViewUrl(wv: WebView, webViewUrl: String?) {
    wv.loadUrl(webViewUrl)
}

@BindingAdapter("lowestPrice")
fun lowestPrice(tv: TextView, value: Int) {
    val df = DecimalFormat("#,###,###")
    tv.text = Html.fromHtml("최저 <b>${df.format(value)}</b>원")
}