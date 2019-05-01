package ru.newuserkk.naukatesting.presentation.view.department


import ru.newuserkk.naukatesting.R

import ru.newuserkk.naukatesting.domain.department.model.Department
import ru.newuserkk.naukatesting.presentation.presenter.department.DepartmentListPresenter
import ru.newuserkk.naukatesting.presentation.view.AbstractAddItemActivity
import ru.newuserkk.naukatesting.presentation.view.AbstractItemRecyclerViewAdapter
import ru.newuserkk.naukatesting.presentation.view.AbstractListActivity

class DepartmentListActivity : AbstractListActivity<Department>() {

    override val presenter = DepartmentListPresenter(this)
    override var adapter: AbstractItemRecyclerViewAdapter<Department> = DepartmentRecyclerViewAdapter(mutableListOf())
    override val activityResId: Int
        get() = R.layout.activity_department_list
    override val toolbarResId: Int
        get() = R.id.toolbar
    override val addButtonResId: Int
        get() = R.id.departmentListAddButton
    override val listResId: Int
        get() = R.id.departmentList
    override val addActivityTypeToken: Class<out AbstractAddItemActivity<Department>>
        get() = AddDepartmentActivity::class.java

}
