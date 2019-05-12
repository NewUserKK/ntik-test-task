package ru.newuserkk.timesheet.presentation.view.department

import kotlinx.android.synthetic.main.activity_department_detail.*
import ru.newuserkk.timesheet.R
import ru.newuserkk.timesheet.domain.department.model.Department
import ru.newuserkk.timesheet.presentation.view.common.AbstractItemDetailActivity

class DepartmentDetailActivity : AbstractItemDetailActivity<Department>() {

    override val layoutResId = R.layout.activity_department_detail

    override fun showInfo(item: Department) {
        department_detail_name_value.text = item.name
    }

}