package ssun.pe.kr.androiddemo.view.main

import android.arch.lifecycle.LifecycleOwner
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import ssun.pe.kr.androiddemo.data.model.Item
import ssun.pe.kr.androiddemo.databinding.ItemMainBinding

class MainAdapter(
        private val eventListener: EventActions,
        private val lifecycleOwner: LifecycleOwner
) : ListAdapter<Item, MainHolder>(SessionDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = ItemMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainHolder(binding, eventListener, lifecycleOwner)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class MainHolder(
        private val binding: ItemMainBinding,
        private val eventListener: EventActions,
        private val lifecycleOwner: LifecycleOwner
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Item) {
        binding.item = item
        binding.eventListener = eventListener
        binding.setLifecycleOwner(lifecycleOwner)
        binding.executePendingBindings()
    }
}

object SessionDiff : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item?, newItem: Item?): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Item?, newItem: Item?): Boolean {
        return oldItem == newItem
    }
}