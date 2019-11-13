package ssun.pe.kr.androiddemo.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LifecycleOwner
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ssun.pe.kr.androiddemo.databinding.ItemShopBinding
import ssun.pe.kr.androiddemo.model.ShopItem
import ssun.pe.kr.androiddemo.presentation.EventActions

class ShopAdapter(
    private val eventListener: EventActions,
    private val lifecycleOwner: LifecycleOwner
) : PagedListAdapter<ShopItem, ShopHolder>(ShopDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopHolder {
        val binding = ItemShopBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShopHolder(binding, eventListener, lifecycleOwner)
    }

    override fun onBindViewHolder(holder: ShopHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class ShopHolder(
    private val binding: ItemShopBinding,
    private val eventListener: EventActions,
    private val lifecycleOwner: LifecycleOwner
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ShopItem?) {
        binding.item = item
        binding.eventListener = eventListener
        binding.lifecycleOwner = lifecycleOwner
        binding.executePendingBindings()
    }
}

object ShopDiff : DiffUtil.ItemCallback<ShopItem>() {
    /**
     * Called to check whether two objects represent the same item.
     */
    override fun areItemsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
        return oldItem == newItem
    }

    /**
     * Called to check whether two items have the same data.
     */
    override fun areContentsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
        return oldItem.productId == newItem.productId
    }
}

@BindingAdapter("items")
fun items(rv: RecyclerView, items: PagedList<ShopItem>?) = items?.let {
    (rv.adapter as? ShopAdapter)?.submitList(items)
}