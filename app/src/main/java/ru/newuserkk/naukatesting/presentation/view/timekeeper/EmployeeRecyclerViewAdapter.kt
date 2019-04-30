package ru.newuserkk.naukatesting.presentation.view.timekeeper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.item_list_timekeeper_detail.view.*
import ru.newuserkk.naukatesting.R
import ru.newuserkk.naukatesting.domain.employee.model.Employee
import ru.newuserkk.naukatesting.domain.employee.model.getFullName

class EmployeeRecyclerViewAdapter(val values: List<Employee>) :
    androidx.recyclerview.widget.RecyclerView.Adapter<EmployeeRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_timekeeper_detail, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = values.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.apply {
            idView.text = position.toString()
            nameView.text = item.getFullName()
            statusView.text = "PS" // TODO
        }
    }

    class ViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
        val idView: TextView = view.timekeeperDetailEmployeeId
        val nameView: TextView = view.timekeeperDetailEmployeeName
        val statusView: TextView = view.timekeeperDetailEmployeeStatus
    }
}