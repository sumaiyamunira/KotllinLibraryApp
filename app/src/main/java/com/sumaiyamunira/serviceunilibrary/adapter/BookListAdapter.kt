package com.sumaiyamunira.serviceunilibrary.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sumaiyamunira.serviceunilibrary.ServiceUniLibraryApp
import com.sumaiyamunira.serviceunilibrary.databinding.LearnItemBookRowBinding
import com.sumaiyamunira.serviceunilibrary.models.BookDTO
import com.sumaiyamunira.serviceunilibrary.utils.loadImageFromResources

class BookListAdapter(
    private val layoutBindingInflater: (LayoutInflater, ViewGroup, Boolean) -> LearnItemBookRowBinding, // Change the return type to LearnItemMainCategoryBinding
    val onItemClick: ((BookDTO) -> Unit)?
) : RecyclerView.Adapter<BookListAdapter.ViewHolder>() {

    private var items = ArrayList<BookDTO>()

    fun addItem(item: BookDTO) {
        this.items.add(item)
        notifyItemInserted(items.size - 1)
    }

    fun addItems(items: List<BookDTO>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun clearItems() {
        this.items.clear()
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: LearnItemBookRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(items[adapterPosition])
            }
        }

        fun bind(item: BookDTO) {
            binding.ivCourse.loadImageFromResources(ServiceUniLibraryApp.getAppInstance(), item.img)
            binding.tvFeatureName.text = item.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = layoutBindingInflater(
            layoutInflater,
            parent,
            false
        ) // Use layoutBindingInflater to inflate the binding layout
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun updateItems(newItems: List<BookDTO>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

}