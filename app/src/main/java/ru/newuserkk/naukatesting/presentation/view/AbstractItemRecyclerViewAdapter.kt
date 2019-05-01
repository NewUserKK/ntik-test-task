package ru.newuserkk.naukatesting.presentation.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class AbstractItemRecyclerViewAdapter<T>(val values: MutableList<T>) :
    androidx.recyclerview.widget.RecyclerView.Adapter<AbstractItemRecyclerViewAdapter.AbstractViewHolder>() {

    protected abstract val listItemResId: Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbstractViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(listItemResId, parent, false)
        return createViewHolder(view)
    }

    override fun getItemCount(): Int = values.size

    override fun onBindViewHolder(holder: AbstractViewHolder, position: Int) {
        val item = values[position]
        fillViewHolder(holder, item)
    }

    abstract fun createViewHolder(view: View): AbstractViewHolder
    abstract fun fillViewHolder(holder: AbstractViewHolder, item: T)

    abstract class AbstractViewHolder(view: View): androidx.recyclerview.widget.RecyclerView.ViewHolder(view)
}
