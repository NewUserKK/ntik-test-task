package ru.newuserkk.timesheet.presentation.view.timekeeper

import kotlinx.android.synthetic.main.fields_timekeeper_detail.*
import ru.newuserkk.timesheet.R
import ru.newuserkk.timesheet.domain.employee.model.getFullName
import ru.newuserkk.timesheet.domain.timekeeper.model.MarkedEmployee
import ru.newuserkk.timesheet.presentation.view.common.AbstractItemDetailActivity

class TimekeeperDetailActivity : AbstractItemDetailActivity<MarkedEmployee>() {

    override val layoutResId = R.layout.activity_timekeeper_detail

    override fun showInfo(item: MarkedEmployee) {
        timekeeper_detail_employee_value.text = item.employee.getFullName()
        timekeeper_detail_status_value.text = item.status.toString()
    }
}