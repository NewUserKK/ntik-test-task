package ru.newuserkk.naukatesting.presentation.view.employee

import ru.newuserkk.naukatesting.R
import ru.newuserkk.naukatesting.domain.employee.model.Employee
import ru.newuserkk.naukatesting.presentation.presenter.common.AbstractListPresenter
import ru.newuserkk.naukatesting.presentation.presenter.employee.EmployeeListPresenter
import ru.newuserkk.naukatesting.presentation.view.common.AbstractItemAddActivity
import ru.newuserkk.naukatesting.presentation.view.common.AbstractItemRecyclerViewAdapter
import ru.newuserkk.naukatesting.presentation.view.common.AbstractListActivity

class EmployeeListActivity : AbstractListActivity<Employee>() {

    override val presenter: AbstractListPresenter<Employee> =
        EmployeeListPresenter(this)
    override val activityResId: Int
        get() = R.layout.activity_employee_list
    override val toolbarResId: Int
        get() = R.id.timekeeper_detail_toolbar
    override val addButtonResId: Int
        get() = R.id.timekeeper_detail_add_button
    override val listResId: Int
        get() = R.id.employee_list
    override val itemAddActivityTypeToken: Class<out AbstractItemAddActivity<Employee>>
        get() = EmployeeAddActivity::class.java
    override val adapter: AbstractItemRecyclerViewAdapter<Employee> = EmployeeRecyclerViewAdapter(mutableListOf())
}
