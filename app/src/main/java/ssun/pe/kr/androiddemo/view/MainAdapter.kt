package ssun.pe.kr.androiddemo.view

import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.main_item.*
import ssun.pe.kr.androiddemo.R
import ssun.pe.kr.androiddemo.data.model.Item
import java.text.DecimalFormat

class MainAdapter : RecyclerView.Adapter<MainHolder>() {

    var mItems: MutableList<Item>? = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.main_item, parent, false))
    }

    override fun getItemCount(): Int = mItems?.size ?: 0

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        mItems?.get(holder.adapterPosition)?.let { item ->
            Picasso.get()
                    .load(item.image)
                    .resize(200, 200)
                    .centerCrop()
                    .into(holder.ivImage)

            holder.tvTitle.text = Html.fromHtml(item.title)

            val df = DecimalFormat("#,###,###")
            holder.tvLPrice.text = Html.fromHtml("최저 <b>${df.format(item.lprice.toInt())}</b>원")
        }
    }
}