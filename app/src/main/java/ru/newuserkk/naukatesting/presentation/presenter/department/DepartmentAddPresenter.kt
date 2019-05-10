package ru.newuserkk.naukatesting.presentation.presenter.department

import kotlinx.coroutines.Dispatchers
import ru.newuserkk.naukatesting.domain.common.Result
import ru.newuserkk.naukatesting.domain.department.DepartmentInteractor
import ru.newuserkk.naukatesting.domain.department.DepartmentInteractorImpl
import ru.newuserkk.naukatesting.domain.department.model.Department
import ru.newuserkk.naukatesting.presentation.presenter.common.AbstractItemAddPresenter
import ru.newuserkk.naukatesting.presentation.view.common.AbstractItemAddActivity
import ru.newuserkk.naukatesting.presentation.view.department.DepartmentAddActivity
import kotlin.coroutines.CoroutineContext

class DepartmentAddPresenter(view: DepartmentAddActivity) : AbstractItemAddPresenter<Department>(view) {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private val departmentInteractor: DepartmentInteractor = DepartmentInteractorImpl()

    override fun createItemFromOptions(options: AbstractItemAddActivity.ItemOptions): Result<Department> {
        (options as DepartmentAddActivity.DepartmentOptions)
        return Result(Department(options.departmentName))
    }

    override suspend fun addItem(item: Department, edit: Boolean): Result<Department> {
        return if (!edit) {
            departmentInteractor.addDepartment(item)
        } else {
            departmentInteractor.editDepartment(item)
        }
    }

    override fun changeItemId(oldItem: Department, newItem: Department) {
        newItem.id = oldItem.id
    }
}