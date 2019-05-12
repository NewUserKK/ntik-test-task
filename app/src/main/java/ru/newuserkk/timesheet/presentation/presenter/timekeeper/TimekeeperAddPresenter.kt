package ru.newuserkk.timesheet.presentation.presenter.timekeeper

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance
import ru.newuserkk.timesheet.TimesheetApp
import ru.newuserkk.timesheet.domain.common.Result
import ru.newuserkk.timesheet.domain.employee.EmployeeInteractor
import ru.newuserkk.timesheet.domain.employee.EmployeeInteractorImpl
import ru.newuserkk.timesheet.domain.timekeeper.CalendarInteractor
import ru.newuserkk.timesheet.domain.timekeeper.CalendarInteractorImpl
import ru.newuserkk.timesheet.domain.timekeeper.model.AttendanceStatus
import ru.newuserkk.timesheet.domain.timekeeper.model.MarkedEmployee
import ru.newuserkk.timesheet.presentation.presenter.common.AbstractItemAddPresenter
import ru.newuserkk.timesheet.presentation.view.common.AbstractItemAddActivity
import ru.newuserkk.timesheet.presentation.view.timekeeper.TimekeeperAddActivity
import kotlin.coroutines.CoroutineContext

class TimekeeperAddPresenter(override val view: TimekeeperAddActivity) :
    AbstractItemAddPresenter<MarkedEmployee>(view), KodeinAware {

    override val kodein by lazy { TimesheetApp.kodein }

    private val calendarInteractor: CalendarInteractor by kodein.instance()
    private val employeeInteractor: EmployeeInteractor by kodein.instance()

    override fun createItemFromOptions(options: AbstractItemAddActivity.ItemOptions): Result<MarkedEmployee> {
        options as TimekeeperAddActivity.MarkedEmployeeOptions
        return calendarInteractor.buildItemFromOptions(options)
    }

    override suspend fun addItem(item: MarkedEmployee): Result<MarkedEmployee> {
        return calendarInteractor.addEmployeeMark(item)
    }

    override suspend fun updateItem(id: Long, item: MarkedEmployee): Result<MarkedEmployee> {
        return calendarInteractor.updateEmployeeMark(id, item)
    }

    override fun getItemId(editingItem: MarkedEmployee): Long {
        return editingItem.id
    }

    fun fillEmployeesSpinner() {
        launch {
            val employees = withContext(Dispatchers.IO) {
                employeeInteractor.getEmployees()
            }
            if (employees.isSuccessful && employees.value != null) {
                view.setupEmployeesAdapter(employees.value, view.itemToEdit?.employee)
            } else {
                view.showAddError(employees.error!!)
            }
        }
    }

    fun fillStatusSpinner() {
        view.setupStatusAdapter(AttendanceStatus.values(), view.itemToEdit?.status)
    }
}
