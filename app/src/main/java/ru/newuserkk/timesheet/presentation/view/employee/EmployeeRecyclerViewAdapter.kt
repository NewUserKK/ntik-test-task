package ru.newuserkk.timesheet.presentation.view.employee

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.item_list_employee.view.*
import ru.newuserkk.timesheet.R
import ru.newuserkk.timesheet.domain.employee.model.Employee
import ru.newuserkk.timesheet.domain.employee.model.getFullName
import ru.newuserkk.timesheet.presentation.view.common.AbstractItemRecyclerViewAdapter


class EmployeeRecyclerViewAdapter(values: MutableList<Employee>): AbstractItemRecyclerViewAdapter<Employee>(values) {
    override val listItemResId = R.layout.item_list_employee

    override fun createViewHolder(view: View): AbstractViewHolder = ViewHolder(view)

    override fun fillViewHolder(holder: AbstractViewHolder, position: Int, item: Employee) {
        holder as ViewHolder
        holder.apply {
            idView.text = item.id.toString()
            nameView.text = item.getFullName()
        }
    }

    class ViewHolder(view: View): AbstractViewHolder(view) {
        val idView: TextView = view.mark_employee_id
        val nameView: TextView = view.mark_employee_name

        override val editButtonView: ImageView = view.employee_edit_button
        override val removeButtonView: ImageView = view.employee_remove_button
    }
}