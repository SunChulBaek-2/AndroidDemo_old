package ssun.pe.kr.androiddemo.view.adapter.holder

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.main_item.view.*
import ssun.pe.kr.androiddemo.R

class MainHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
                .inflate(R.layout.main_item, parent, false)) {

    val tvTitle: TextView = itemView.tvTitle
    val tvLPrice: TextView = itemView.tvLPrice
    val ivImage: ImageView = itemView.ivImage
}