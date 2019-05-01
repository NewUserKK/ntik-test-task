package ru.newuserkk.naukatesting.presentation.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class AbstractItemRecyclerViewAdapter<T>(val values: MutableList<T>) :
    androidx.recyclerview.widget.RecyclerView.Adapter<AbstractItemRecyclerViewAdapter.ViewHolder>() {

    protected abstract val listResId: Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(listResId, parent, false)
        return createViewHolder(view)
    }

    override fun getItemCount(): Int = values.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        fillViewHolder(holder, item)
    }

    abstract fun createViewHolder(view: View): ViewHolder
    abstract fun fillViewHolder(holder: ViewHolder, item: T)

    abstract class ViewHolder(view: View): androidx.recyclerview.widget.RecyclerView.ViewHolder(view)
}
