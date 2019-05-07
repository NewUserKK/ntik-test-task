package ru.newuserkk.naukatesting.presentation.view.timekeeper

import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.item_list_timekeeper.view.*
import ru.newuserkk.naukatesting.R
import ru.newuserkk.naukatesting.domain.employee.model.getFullName
import ru.newuserkk.naukatesting.domain.timekeeper.model.MarkedEmployee
import ru.newuserkk.naukatesting.presentation.view.common.AbstractItemRecyclerViewAdapter

class MarkedEmployeeRecyclerViewAdapter(values: MutableList<MarkedEmployee> = mutableListOf()): AbstractItemRecyclerViewAdapter<MarkedEmployee>(values) {
    override val listItemResId: Int
        get() = R.layout.item_list_timekeeper

    override fun createViewHolder(view: View): AbstractViewHolder = ViewHolder(view)

    override fun fillViewHolder(holder: AbstractViewHolder, position: Int, item: MarkedEmployee) {
        holder as ViewHolder
        holder.apply {
            idView.text = item.employee.id.toString()
            nameView.text = item.employee.getFullName()
            statusView.text = item.status.value
        }
    }

    class ViewHolder(view: View): AbstractViewHolder(view) {
        val idView: TextView = view.employee_id
        val nameView: TextView = view.employee_name
        val statusView: TextView = view.employee_status
    }
}