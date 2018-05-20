package ssun.pe.kr.androiddemo.view.adapter

import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.ViewGroup
import com.bumptech.glide.Glide
import ssun.pe.kr.androiddemo.data.model.Item
import ssun.pe.kr.androiddemo.view.adapter.holder.MainHolder
import java.text.DecimalFormat

class MainAdapter(private val mItems: List<Item>) : RecyclerView.Adapter<MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder
            = MainHolder(parent)

    override fun getItemCount(): Int = mItems.size

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        Glide.with(holder.ivImage)
                .load(mItems[holder.adapterPosition].image)
                .into(holder.ivImage)

        holder.tvTitle.text = Html.fromHtml(mItems[holder.adapterPosition].title)
        val df = DecimalFormat("#,###,###")
        holder.tvLPrice.text = Html.fromHtml(
                "최저 <b>${df.format(mItems[holder.adapterPosition].lprice.toInt())}</b>원")
    }
}