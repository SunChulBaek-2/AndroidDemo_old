package ssun.pe.kr.androiddemo.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LifecycleOwner
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ssun.pe.kr.androiddemo.databinding.ItemImageBinding
import ssun.pe.kr.androiddemo.model.ImageItem
import ssun.pe.kr.androiddemo.presentation.EventActions

class ImageAdapter(
    private val eventListener: EventActions,
    private val lifecycleOwner: LifecycleOwner
) : PagedListAdapter<ImageItem, ImageHolder>(ImageDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        val binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageHolder(binding, eventListener, lifecycleOwner)
    }

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class ImageHolder(
    private val binding: ItemImageBinding,
    private val eventListener: EventActions,
    private val lifecycleOwner: LifecycleOwner
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ImageItem?) {
        binding.item = item
        binding.eventListener = eventListener
        binding.lifecycleOwner = lifecycleOwner
        binding.executePendingBindings()
    }
}

object ImageDiff : DiffUtil.ItemCallback<ImageItem>() {
    override fun areItemsTheSame(oldItem: ImageItem, newItem: ImageItem): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: ImageItem, newItem: ImageItem): Boolean =
        oldItem == newItem
}

@BindingAdapter("items")
fun items(rv: RecyclerView, items: PagedList<ImageItem>?) = items?.let {
    (rv.adapter as? ImageAdapter)?.submitList(items)
}