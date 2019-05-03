package ru.newuserkk.naukatesting.presentation.view.department

import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.item_list_department.view.*
import ru.newuserkk.naukatesting.R
import ru.newuserkk.naukatesting.domain.department.model.Department
import ru.newuserkk.naukatesting.presentation.view.common.AbstractItemRecyclerViewAdapter

class DepartmentRecyclerViewAdapter(values: MutableList<Department>) :
    AbstractItemRecyclerViewAdapter<Department>(values) {

    override val listItemResId: Int
        get() = R.layout.item_list_department

    override fun createViewHolder(view: View): AbstractItemRecyclerViewAdapter.AbstractViewHolder = ViewHolder(view)

    override fun fillViewHolder(holder: AbstractItemRecyclerViewAdapter.AbstractViewHolder, position: Int, item: Department) {
        (holder as ViewHolder).apply {
            nameView.text = item.name
        }
    }

    class ViewHolder(view: View) : AbstractViewHolder(view) {
        val nameView: TextView = view.department_name
    }
}