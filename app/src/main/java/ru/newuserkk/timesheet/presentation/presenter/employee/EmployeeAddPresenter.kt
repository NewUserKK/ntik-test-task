package ru.newuserkk.timesheet.presentation.presenter.employee

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance
import ru.newuserkk.timesheet.TimesheetApp
import ru.newuserkk.timesheet.domain.common.Result
import ru.newuserkk.timesheet.domain.department.DepartmentInteractor
import ru.newuserkk.timesheet.domain.department.DepartmentInteractorImpl
import ru.newuserkk.timesheet.domain.employee.EmployeeInteractor
import ru.newuserkk.timesheet.domain.employee.EmployeeInteractorImpl
import ru.newuserkk.timesheet.domain.employee.model.Employee
import ru.newuserkk.timesheet.presentation.presenter.common.AbstractItemAddPresenter
import ru.newuserkk.timesheet.presentation.view.common.AbstractItemAddActivity
import ru.newuserkk.timesheet.presentation.view.employee.EmployeeAddActivity
import kotlin.coroutines.CoroutineContext

class EmployeeAddPresenter(override val view: EmployeeAddActivity) :
    AbstractItemAddPresenter<Employee>(view), KodeinAware {

    override val kodein by lazy { TimesheetApp.kodein }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private val departmentInteractor: DepartmentInteractor by kodein.instance()
    private val employeeInteractor: EmployeeInteractor by kodein.instance()


    override fun createItemFromOptions(options: AbstractItemAddActivity.ItemOptions): Result<Employee> {
        options as EmployeeAddActivity.EmployeeOptions
        return employeeInteractor.buildEmployeeFromOptions(options)
    }

    override suspend fun addItem(item: Employee): Result<Employee> {
        return employeeInteractor.addEmployee(item)
    }

    override suspend fun updateItem(id: Long, item: Employee): Result<Employee> {
        return employeeInteractor.updateEmployee(id, item)
    }

    override fun getItemId(editingItem: Employee): Long {
        return editingItem.id!!
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
}