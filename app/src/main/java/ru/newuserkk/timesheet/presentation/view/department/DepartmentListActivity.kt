package ru.newuserkk.timesheet.presentation.view.department


import ru.newuserkk.timesheet.R
import ru.newuserkk.timesheet.domain.department.model.Department
import ru.newuserkk.timesheet.presentation.presenter.common.AbstractListPresenter
import ru.newuserkk.timesheet.presentation.presenter.department.DepartmentListPresenter
import ru.newuserkk.timesheet.presentation.view.common.AbstractItemRecyclerViewAdapter
import ru.newuserkk.timesheet.presentation.view.common.AbstractListActivity

class DepartmentListActivity : AbstractListActivity<Department>() {

    override val layoutResId = R.layout.activity_department_list
    override val toolbarResId = R.id.timekeeper_list_toolbar
    override val addButtonResId = R.id.department_list_add_button
    override val listResId = R.id.department_list
    override val contentResId = R.id.department_list_content
    override val progressBarResId = R.id.department_list_progress_bar

    override val itemDetailActivityTypeToken = DepartmentDetailActivity::class.java
    override val itemAddActivityTypeToken = DepartmentAddActivity::class.java

    override var adapter: AbstractItemRecyclerViewAdapter<Department> =
        DepartmentRecyclerViewAdapter(mutableListOf())


    override fun initPresenter(): AbstractListPresenter<Department> {
        return DepartmentListPresenter(this)
    }

    override fun getFillErrorMessage(): String = getString(R.string.department_load_fail)

    override fun getRemoveErrorMessage(): String = getString(R.string.remove_item_fail)
}
