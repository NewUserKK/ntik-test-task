package ru.newuserkk.timesheet.presentation.presenter.department

import kotlinx.coroutines.Dispatchers
import ru.newuserkk.timesheet.domain.common.Result
import ru.newuserkk.timesheet.domain.department.DepartmentInteractor
import ru.newuserkk.timesheet.domain.department.DepartmentInteractorImpl
import ru.newuserkk.timesheet.domain.department.model.Department
import ru.newuserkk.timesheet.presentation.presenter.common.AbstractItemAddPresenter
import ru.newuserkk.timesheet.presentation.view.common.AbstractItemAddActivity
import ru.newuserkk.timesheet.presentation.view.department.DepartmentAddActivity
import kotlin.coroutines.CoroutineContext

class DepartmentAddPresenter(view: DepartmentAddActivity) : AbstractItemAddPresenter<Department>(view) {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private val departmentInteractor: DepartmentInteractor = DepartmentInteractorImpl()

    override fun createItemFromOptions(options: AbstractItemAddActivity.ItemOptions): Result<Department> {
        (options as DepartmentAddActivity.DepartmentOptions)
        return Result(Department(options.departmentName))
    }

    override suspend fun addItem(item: Department): Result<Department> {
        return departmentInteractor.addDepartment(item)
    }

    override suspend fun updateItem(id: Long, item: Department): Result<Department> {
        return departmentInteractor.updateDepartment(id, item)
    }

    override fun getItemId(editingItem: Department): Long {
        return editingItem.id!!
    }
}