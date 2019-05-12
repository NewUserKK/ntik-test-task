package ru.newuserkk.timesheet.presentation.view.employee

import ru.newuserkk.timesheet.R
import ru.newuserkk.timesheet.domain.employee.model.Employee
import ru.newuserkk.timesheet.presentation.presenter.common.AbstractListPresenter
import ru.newuserkk.timesheet.presentation.presenter.employee.EmployeeListPresenter
import ru.newuserkk.timesheet.presentation.view.common.AbstractItemRecyclerViewAdapter
import ru.newuserkk.timesheet.presentation.view.common.AbstractListActivity

class EmployeeListActivity : AbstractListActivity<Employee>() {

    override val layoutResId = R.layout.activity_employee_list
    override val toolbarResId = R.id.employee_list_toolbar
    override val addButtonResId = R.id.employee_list_add_button
    override val listResId = R.id.employee_list

    override val adapter: AbstractItemRecyclerViewAdapter<Employee> = EmployeeRecyclerViewAdapter(mutableListOf())

    override val itemDetailActivityTypeToken = EmployeeDetailActivity::class.java
    override val itemAddActivityTypeToken = EmployeeAddActivity::class.java

    override fun initPresenter(): AbstractListPresenter<Employee> {
        return EmployeeListPresenter(this)
    }

    override fun getFillErrorMessage(): String = getString(R.string.employees_load_fail)

    override fun getRemoveErrorMessage(): String = getString(R.string.remove_item_fail)
}
