package ru.newuserkk.timesheet.presentation.view.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

abstract class AbstractItemRecyclerViewAdapter<T>(val values: MutableList<T> = mutableListOf()) :
    androidx.recyclerview.widget.RecyclerView.Adapter<AbstractItemRecyclerViewAdapter.AbstractViewHolder>() {

    protected abstract val listItemResId: Int
    var onItemClickListener: ((View) -> Unit)? = null
    var onRemoveClickListener: ((View) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbstractViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(listItemResId, parent, false)
        return createViewHolder(view)
    }

    override fun getItemCount(): Int = values.size

    override fun onBindViewHolder(holder: AbstractViewHolder, position: Int) {
        val item = values[position]
        holder.apply {
            itemView.tag = item
            itemView.setOnClickListener(onItemClickListener)

            removeButtonView.tag = item
            removeButtonView.setOnClickListener(onRemoveClickListener)
        }

        fillViewHolder(holder, position, item)
    }


    abstract fun createViewHolder(view: View): AbstractViewHolder
    abstract fun fillViewHolder(holder: AbstractViewHolder, position: Int, item: T)

    abstract class AbstractViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
        abstract val removeButtonView: ImageView
    }
}
