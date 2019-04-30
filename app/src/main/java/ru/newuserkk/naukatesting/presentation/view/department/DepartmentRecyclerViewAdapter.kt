package ru.newuserkk.naukatesting.presentation.view.department

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.item_list_department.view.*
import ru.newuserkk.naukatesting.R
import ru.newuserkk.naukatesting.domain.department.model.Department

class DepartmentRecyclerViewAdapter(val values: MutableList<Department>) :
    androidx.recyclerview.widget.RecyclerView.Adapter<DepartmentRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_department, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = values.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.apply {
            nameView.text = item.name
        }
    }

    class ViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
        val nameView: TextView = view.departmentName
    }
}