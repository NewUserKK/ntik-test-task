package ru.newuserkk.timesheet.presentation.presenter.employee

import kotlinx.coroutines.Dispatchers
import ru.newuserkk.timesheet.domain.common.Result
import ru.newuserkk.timesheet.domain.employee.EmployeeInteractor
import ru.newuserkk.timesheet.domain.employee.EmployeeInteractorImpl
import ru.newuserkk.timesheet.domain.employee.model.Employee
import ru.newuserkk.timesheet.presentation.presenter.common.AbstractListPresenter
import ru.newuserkk.timesheet.presentation.view.employee.EmployeeListActivity
import kotlin.coroutines.CoroutineContext

class EmployeeListPresenter(view: EmployeeListActivity): AbstractListPresenter<Employee>(view) {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private val interactor: EmployeeInteractor = EmployeeInteractorImpl()

    override suspend fun getItems(): Result<List<Employee>> {
        return interactor.getEmployees()
    }

    override suspend fun removeItemImpl(item: Employee): Result<Employee> {
        return interactor.removeEmployee(item)
    }
}