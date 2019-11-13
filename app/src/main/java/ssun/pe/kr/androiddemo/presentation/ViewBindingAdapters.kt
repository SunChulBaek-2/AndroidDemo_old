package ssun.pe.kr.androiddemo.presentation

import android.os.Build
import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.text.DecimalFormat

@BindingAdapter("goneUnless")
fun goneUnless(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("refreshEnabled")
fun refreshEnabled(swipe: SwipeRefreshLayout, refreshEnabled: Boolean) {
    swipe.isEnabled = refreshEnabled
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

// ViewPager =======================================================================================
@BindingAdapter("current")
fun setCurrent(vp: ViewPager, current: Int) {
    vp.currentItem = current
}

@InverseBindingAdapter(attribute = "current", event = "currentChanged")
fun getCurrent(vp: ViewPager): Int = vp.currentItem

@BindingAdapter("currentChanged")
fun currentChanged(vp: ViewPager, listener: InverseBindingListener) {
    vp.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) { }

        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) { }

        override fun onPageSelected(position: Int) {
            listener.onChange()
        }
    })
}

// SwipeRefreshLayout ==============================================================================
@BindingAdapter("isRefreshing")
fun setIsRefreshing(swipe: SwipeRefreshLayout, isRefreshing: Boolean) {
    swipe.isRefreshing = isRefreshing
}

@InverseBindingAdapter(attribute = "isRefreshing", event = "refreshChanged")
fun getIsRefreshing(swipe: SwipeRefreshLayout): Boolean = swipe.isRefreshing

@BindingAdapter("refreshChanged")
fun refreshChanged(swipe: SwipeRefreshLayout, listener: InverseBindingListener) {
    swipe.setOnRefreshListener {
        listener.onChange()
    }
}