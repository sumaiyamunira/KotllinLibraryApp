package com.sumaiyamunira.serviceunilibrary.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sumaiyamunira.serviceunilibrary.ServiceUniLibraryApp
import com.sumaiyamunira.serviceunilibrary.databinding.LearnItemMainCategoryBinding
import com.sumaiyamunira.serviceunilibrary.models.LearnCategory
import com.sumaiyamunira.serviceunilibrary.utils.loadImageFromResources

class HomeMainItemListAdapter(
    private val layoutBindingInflater: (LayoutInflater, ViewGroup, Boolean) -> LearnItemMainCategoryBinding, // Change the return type to LearnItemMainCategoryBinding
    val onItemClick: ((LearnCategory) -> Unit)?
) : RecyclerView.Adapter<HomeMainItemListAdapter.ViewHolder>() {

    private var items = ArrayList<LearnCategory>()

    fun addItem(item: LearnCategory) {
        this.items.add(item)
        notifyItemInserted(items.size - 1)
    }

    fun addItems(items: List<LearnCategory>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun clearItems() {
        this.items.clear()
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: LearnItemMainCategoryBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(items[adapterPosition])
            }
        }

        fun bind(item: LearnCategory) {

            binding.ivCategory.loadImageFromResources(ServiceUniLibraryApp.getAppInstance(), item.img)
            binding.tvCategoryName.text = item.name

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = layoutBindingInflater(layoutInflater, parent, false) // Use layoutBindingInflater to inflate the binding layout
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }
}
