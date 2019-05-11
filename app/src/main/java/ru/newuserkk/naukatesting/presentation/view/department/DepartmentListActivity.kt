package ru.newuserkk.naukatesting.presentation.view.department


import ru.newuserkk.naukatesting.R

import ru.newuserkk.naukatesting.domain.department.model.Department
import ru.newuserkk.naukatesting.presentation.presenter.common.AbstractListPresenter
import ru.newuserkk.naukatesting.presentation.presenter.department.DepartmentListPresenter
import ru.newuserkk.naukatesting.presentation.view.common.AbstractItemRecyclerViewAdapter
import ru.newuserkk.naukatesting.presentation.view.common.AbstractListActivity

class DepartmentListActivity : AbstractListActivity<Department>() {

    override var adapter: AbstractItemRecyclerViewAdapter<Department> =
        DepartmentRecyclerViewAdapter(mutableListOf()).apply {
            onRemoveClickListener = {
                presenter.removeItem(it.tag as Department)
            }
        }
    override val activityLayoutResId = R.layout.activity_department_list
    override val toolbarResId = R.id.timekeeper_list_toolbar
    override val addButtonResId = R.id.department_list_add_button
    override val listResId = R.id.department_list
    override val itemAddActivityTypeToken =
        DepartmentAddActivity::class.java

    override fun initPresenter(): AbstractListPresenter<Department> {
        return DepartmentListPresenter(this)
    }

    override fun getFillErrorMessage(): String = getString(R.string.department_load_fail)

    override fun getRemoveErrorMessage(): String = getString(R.string.remove_item_fail)
}
