package com.sumaiyamunira.serviceunilibrary.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sumaiyamunira.serviceunilibrary.ServiceUniLibraryApp
import com.sumaiyamunira.serviceunilibrary.databinding.LearnItemFeaturedBinding
import com.sumaiyamunira.serviceunilibrary.models.LearnFeatured
import com.sumaiyamunira.serviceunilibrary.utils.loadImageFromResources

class LearnerRecyclerViewAdapter(
    private val layoutBindingInflater: (LayoutInflater, ViewGroup, Boolean) -> LearnItemFeaturedBinding,
    val onItemClick: ((LearnFeatured) -> Unit)?
) : RecyclerView.Adapter<LearnerRecyclerViewAdapter.ViewHolder>() {

    private var items = ArrayList<LearnFeatured>()

    fun addItem(item: LearnFeatured) {
        this.items.add(item)
        notifyItemInserted(items.size - 1)
    }

    fun addItems(items: List<LearnFeatured>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun clearItems() {
        this.items.clear()
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: LearnItemFeaturedBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(items[adapterPosition])
            }
        }

        fun bind(item: LearnFeatured) {
            binding.imgWalk1.loadImageFromResources(ServiceUniLibraryApp.getAppInstance(), item.img)
            binding.tvFeatureName.text = item.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = layoutBindingInflater(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }
}
