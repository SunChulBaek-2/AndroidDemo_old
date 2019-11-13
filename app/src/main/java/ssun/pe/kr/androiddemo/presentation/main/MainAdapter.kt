package ssun.pe.kr.androiddemo.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ssun.pe.kr.androiddemo.databinding.ItemMainBinding
import ssun.pe.kr.androiddemo.model.Item

class MainAdapter(
    private val eventListener: MainViewModel,
    private val lifecycleOwner: LifecycleOwner
) : PagedListAdapter<Item, MainHolder>(SessionDiff) {

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
    private val eventListener: MainViewModel,
    private val lifecycleOwner: LifecycleOwner
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Item?) {
        binding.item = item
        binding.eventListener = eventListener
        binding.lifecycleOwner = lifecycleOwner
        binding.executePendingBindings()
    }
}

object SessionDiff : DiffUtil.ItemCallback<Item>() {
    /**
     * Called to check whether two objects represent the same item.
     */
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem == newItem
    }

    /**
     * Called to check whether two items have the same data.
     */
    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.productId == newItem.productId
    }
}