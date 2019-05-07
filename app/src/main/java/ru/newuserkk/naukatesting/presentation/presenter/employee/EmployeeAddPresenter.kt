package ru.newuserkk.naukatesting.presentation.presenter.employee

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.newuserkk.naukatesting.domain.common.Result
import ru.newuserkk.naukatesting.domain.department.DepartmentInteractor
import ru.newuserkk.naukatesting.domain.department.DepartmentInteractorImpl
import ru.newuserkk.naukatesting.domain.employee.EmployeeInteractor
import ru.newuserkk.naukatesting.domain.employee.EmployeeInteractorImpl
import ru.newuserkk.naukatesting.domain.employee.model.Employee
import ru.newuserkk.naukatesting.presentation.presenter.common.AbstractItemAddPresenter
import ru.newuserkk.naukatesting.presentation.view.common.AbstractItemAddActivity
import ru.newuserkk.naukatesting.presentation.view.employee.EmployeeAddActivity
import kotlin.coroutines.CoroutineContext

class EmployeeAddPresenter(override val view: EmployeeAddActivity): AbstractItemAddPresenter<Employee>(view) {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private val departmentInteractor: DepartmentInteractor = DepartmentInteractorImpl()
    private val employeeInteractor: EmployeeInteractor = EmployeeInteractorImpl()

    override fun createItemFromOptions(options: AbstractItemAddActivity.ItemOptions): Employee {
        options as EmployeeAddActivity.EmployeeOptions
        // TODO: crash on null department, handle result
        return employeeInteractor.buildEmployee(options).value!!
    }

    override suspend fun addItem(item: Employee): Result<Employee> =
        employeeInteractor.addEmployee(item)

    fun fillDepartmentsSpinner() {
        launch {
            val result = departmentInteractor.getDepartments()
            if (result.isSuccessful && result.value != null) {
                view.setupDepartmentsAdapter(result.value)
            } else {
                view.showAddError(result.error!!)
            }
        }
    }
}