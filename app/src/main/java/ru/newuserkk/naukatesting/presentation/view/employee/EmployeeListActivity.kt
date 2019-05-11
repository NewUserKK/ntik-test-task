package ru.newuserkk.naukatesting.presentation.view.employee

import android.os.Bundle
import ru.newuserkk.naukatesting.R
import ru.newuserkk.naukatesting.domain.employee.model.Employee
import ru.newuserkk.naukatesting.presentation.presenter.common.AbstractListPresenter
import ru.newuserkk.naukatesting.presentation.presenter.employee.EmployeeListPresenter
import ru.newuserkk.naukatesting.presentation.view.common.AbstractItemAddActivity
import ru.newuserkk.naukatesting.presentation.view.common.AbstractItemRecyclerViewAdapter
import ru.newuserkk.naukatesting.presentation.view.common.AbstractListActivity

class EmployeeListActivity : AbstractListActivity<Employee>() {

    override val activityLayoutResId = R.layout.activity_employee_list
    override val toolbarResId = R.id.employee_list_toolbar
    override val addButtonResId = R.id.employee_list_add_button
    override val listResId = R.id.employee_list
    override val itemAddActivityTypeToken = EmployeeAddActivity::class.java
    override val adapter: AbstractItemRecyclerViewAdapter<Employee> =
        EmployeeRecyclerViewAdapter(mutableListOf()).apply {
            onRemoveClickListener = {
                presenter.removeItem(it.tag as Employee)
            }
        }

    override fun initPresenter(): AbstractListPresenter<Employee> {
        return EmployeeListPresenter(this)
    }

    override fun getFillErrorMessage(): String = getString(R.string.employees_load_fail)

    override fun getRemoveErrorMessage(): String = getString(R.string.remove_item_fail)
}
