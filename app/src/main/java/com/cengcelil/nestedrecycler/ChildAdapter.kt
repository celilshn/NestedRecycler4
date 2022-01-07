package com.cengcelil.nestedrecycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cengcelil.nestedrecycler.databinding.ChildItemBinding

class ChildAdapter(
    val listDeep: MutableList<ParentItem>,
    val pos: Int,
    val checkedChanged: (parentItem: MutableList<ParentItem>) -> Unit
) :
    ListAdapter<ChildItem, ChildAdapter.ChildItemHolder>(DIFF_CALLBACK) {

    init {
        submitList(listDeep.get(pos).childList)
    }

    override fun submitList(list: List<ChildItem>?) {
        super.submitList(list?.let { ArrayList(it) })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildItemHolder {
        return ChildItemHolder(
            ChildItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ChildItemHolder, position: Int) {
        holder.bind(position)
    }


    override fun getItemCount(): Int {
        return currentList.size
    }

    inner class ChildItemHolder(val binding: ChildItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val item = getItem(position)
            binding.checkBox.text = item.name
            binding.checkBox.isChecked = item.isSelected
            binding.checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
                item.isSelected = isChecked
                checkedChanged.invoke(listDeep)
            }
        }
    }


    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<ChildItem> =
            object : DiffUtil.ItemCallback<ChildItem>() {
                override fun areItemsTheSame(oldItem: ChildItem, newItem: ChildItem): Boolean {
                    return oldItem == newItem
                }

                override fun areContentsTheSame(oldItem: ChildItem, newItem: ChildItem): Boolean {
                    // check for contents
                    return oldItem.isSelected == newItem.isSelected && oldItem.name == newItem.name
                }
            }
    }


}
