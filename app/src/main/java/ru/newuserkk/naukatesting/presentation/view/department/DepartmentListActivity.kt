package ru.newuserkk.naukatesting.presentation.view.department


import ru.newuserkk.naukatesting.R

import ru.newuserkk.naukatesting.domain.department.model.Department
import ru.newuserkk.naukatesting.presentation.presenter.department.DepartmentListPresenter
import ru.newuserkk.naukatesting.presentation.view.common.AbstractItemAddActivity
import ru.newuserkk.naukatesting.presentation.view.common.AbstractItemRecyclerViewAdapter
import ru.newuserkk.naukatesting.presentation.view.common.AbstractListActivity

class DepartmentListActivity : AbstractListActivity<Department>() {

    override val presenter = DepartmentListPresenter(this)
    override var adapter: AbstractItemRecyclerViewAdapter<Department> = DepartmentRecyclerViewAdapter(mutableListOf())
    override val activityResId: Int
        get() = R.layout.activity_department_list
    override val toolbarResId: Int
        get() = R.id.timekeeper_detail_toolbar
    override val addButtonResId: Int
        get() = R.id.department_list_add_button
    override val listResId: Int
        get() = R.id.department_list
    override val itemAddActivityTypeToken: Class<out AbstractItemAddActivity<Department>>
        get() = DepartmentAddActivity::class.java

}
