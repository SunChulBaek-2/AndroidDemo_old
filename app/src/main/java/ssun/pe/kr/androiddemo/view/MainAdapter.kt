package ssun.pe.kr.androiddemo.view

import android.databinding.BindingAdapter
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import ssun.pe.kr.androiddemo.R
import ssun.pe.kr.androiddemo.data.model.Item
import ssun.pe.kr.androiddemo.databinding.MainItemBinding
import java.text.DecimalFormat

@BindingAdapter("setTitle")
fun setText(tv: TextView, text: String) {
    tv.text = Html.fromHtml(text)
}

@BindingAdapter("setDesc")
fun setDesc(tv: TextView, text: String) {
        val df = DecimalFormat("#,###,###")
        tv.text = Html.fromHtml("최저 <b>${df.format(text.toInt())}</b>원")
}

@BindingAdapter("setProfile")
fun setProfile(iv: ImageView, url: String) {
        Glide.with(iv).load(url).into(iv)
}

class MainAdapter(private val mItems: MutableList<Item>) : RecyclerView.Adapter<MainAdapter.MainHolder>() {

    class MainHolder(val binding: MainItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding: MainItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.main_item, parent, false)
        return MainHolder(binding)
    }

    override fun getItemCount(): Int = mItems.size

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.binding.item = mItems[holder.adapterPosition]
    }

    fun replaceData(items: List<Item>) {
        mItems.clear()
        mItems.addAll(items)
        notifyDataSetChanged()
    }
}