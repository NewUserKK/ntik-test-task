package ru.newuserkk.naukatesting.presentation.presenter.department

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.newuserkk.naukatesting.domain.common.Result
import ru.newuserkk.naukatesting.domain.department.DepartmentInteractor
import ru.newuserkk.naukatesting.domain.department.DepartmentInteractorImpl
import ru.newuserkk.naukatesting.domain.department.model.Department
import ru.newuserkk.naukatesting.presentation.presenter.AbstractAddItemPresenter
import ru.newuserkk.naukatesting.presentation.view.AbstractAddItemActivity
import ru.newuserkk.naukatesting.presentation.view.department.AddDepartmentActivity
import kotlin.coroutines.CoroutineContext

class AddDepartmentPresenter(view: AddDepartmentActivity) : AbstractAddItemPresenter<Department>(view) {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private val departmentInteractor: DepartmentInteractor = DepartmentInteractorImpl()

    override fun createItemFromOptions(options: AbstractAddItemActivity.ItemOptions): Department {
        (options as AddDepartmentActivity.DepartmentOptions)
        return Department(options.departmentName)
    }

    override suspend fun addItem(item: Department): Result<Department> =
        departmentInteractor.addDepartment(item)
}