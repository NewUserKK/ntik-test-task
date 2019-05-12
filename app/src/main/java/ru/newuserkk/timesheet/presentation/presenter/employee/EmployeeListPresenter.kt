package ru.newuserkk.timesheet.presentation.presenter.employee

import kotlinx.coroutines.Dispatchers
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance
import ru.newuserkk.timesheet.TimesheetApp
import ru.newuserkk.timesheet.domain.common.Result
import ru.newuserkk.timesheet.domain.employee.EmployeeInteractor
import ru.newuserkk.timesheet.domain.employee.EmployeeInteractorImpl
import ru.newuserkk.timesheet.domain.employee.model.Employee
import ru.newuserkk.timesheet.presentation.presenter.common.AbstractListPresenter
import ru.newuserkk.timesheet.presentation.view.employee.EmployeeListActivity
import kotlin.coroutines.CoroutineContext

class EmployeeListPresenter(view: EmployeeListActivity) :
    AbstractListPresenter<Employee>(view), KodeinAware {

    override val kodein by lazy { TimesheetApp.kodein }


    private val interactor: EmployeeInteractor by kodein.instance()

    override suspend fun getItems(): Result<List<Employee>> {
        return interactor.getEmployees()
    }

    override suspend fun removeItemImpl(item: Employee): Result<Employee> {
        return interactor.removeEmployee(item)
    }
}