package com.sumaiyamunira.serviceunilibrary.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sumaiyamunira.serviceunilibrary.ServiceUniLibraryApp
import com.sumaiyamunira.serviceunilibrary.databinding.LearnItemSearchBinding
import com.sumaiyamunira.serviceunilibrary.models.LearnPeople
import com.sumaiyamunira.serviceunilibrary.utils.loadImageFromResources

class AuthorListAdapter(
    private val layoutBindingInflater: (LayoutInflater, ViewGroup, Boolean) -> LearnItemSearchBinding,
    val onItemClick: ((LearnPeople) -> Unit)?
) : RecyclerView.Adapter<AuthorListAdapter.ViewHolder>() {

    private var items = ArrayList<LearnPeople>()

    fun addItem(item: LearnPeople) {
        this.items.add(item)
        notifyItemInserted(items.size - 1)
    }

    fun addItems(items: List<LearnPeople>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun clearItems() {
        this.items.clear()
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: LearnItemSearchBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(items[adapterPosition])
            }
        }

        fun bind(item: LearnPeople) {
            binding.ivUser.loadImageFromResources(ServiceUniLibraryApp.getAppInstance(), item.img)
            binding.tvUserName.text = item.name
            binding.tvSubject.text = item.subject

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