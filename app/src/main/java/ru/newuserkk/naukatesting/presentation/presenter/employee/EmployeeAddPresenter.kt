package ru.newuserkk.naukatesting.presentation.presenter.employee

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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

class EmployeeAddPresenter(override val view: EmployeeAddActivity) : AbstractItemAddPresenter<Employee>(view) {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private val departmentInteractor: DepartmentInteractor = DepartmentInteractorImpl()
    private val employeeInteractor: EmployeeInteractor = EmployeeInteractorImpl()

    override fun createItemFromOptions(options: AbstractItemAddActivity.ItemOptions): Result<Employee> {
        options as EmployeeAddActivity.EmployeeOptions
        return employeeInteractor.buildEmployee(options)
    }

    override suspend fun addItem(item: Employee): Result<Employee> {
        return employeeInteractor.addEmployee(item)
    }

    fun fillDepartmentsSpinner() {
        launch {
            Log.d(LOG_TAG, "Filling departments...")
            val result = withContext(Dispatchers.IO) {
                departmentInteractor.getDepartments()
            }
            Log.d(LOG_TAG, "OK")
            if (result.isSuccessful && result.value != null) {
                view.setupDepartmentsAdapter(
                    result.value,
                    result.value.find { it.id == view.itemToEdit?.departmentId }
                )
            } else {
                view.showAddError(result.error!!)
            }
        }
    }

    override fun changeItemId(editingItem: Employee, itemToAdd: Employee) {
        itemToAdd.id = editingItem.id
    }
}