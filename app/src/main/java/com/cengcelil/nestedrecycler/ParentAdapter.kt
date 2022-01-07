package com.cengcelil.nestedrecycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cengcelil.nestedrecycler.databinding.ParentItemBinding

class ParentAdapter(val list_deep: MutableList<ParentItem>) : ListAdapter<ParentItem, ParentAdapter.ParentItemHolder>(DIFF_CALLBACK) {
    override fun submitList(list: List<ParentItem>?) {
        super.submitList(list?.let { ArrayList(it) })
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentItemHolder {
        return ParentItemHolder(
            ParentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ParentItemHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }


    inner class ParentItemHolder(val binding: ParentItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            val item = getItem(position)
            with(binding) {
                val adapter = ChildAdapter(list_deep,position) { pi->
                    submitList(pi)
                }
                parentRecycler.adapter = adapter
                adapter.submitList(item.childList)
                checkBox.text = item.className
                checkBox.isChecked = item.childList.size == item.childList.filter { it.isSelected }.size
            }
        }
    }


    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<ParentItem> =
            object : DiffUtil.ItemCallback<ParentItem>() {
                override fun areItemsTheSame(oldItem: ParentItem, newItem: ParentItem): Boolean {
                    return oldItem.hashCode() == newItem.hashCode()
                }

                override fun areContentsTheSame(oldItem: ParentItem, newItem: ParentItem): Boolean {
                    // check for contents
                    return oldItem.isSelect == newItem.isSelect && oldItem.childList == newItem.childList && oldItem.className == newItem.className
                }
            }
    }
}

