package ru.newuserkk.naukatesting.presentation.presenter.timekeeper

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.newuserkk.naukatesting.domain.common.Result
import ru.newuserkk.naukatesting.domain.employee.EmployeeInteractor
import ru.newuserkk.naukatesting.domain.employee.EmployeeInteractorImpl
import ru.newuserkk.naukatesting.domain.timekeeper.CalendarInteractor
import ru.newuserkk.naukatesting.domain.timekeeper.CalendarInteractorImpl
import ru.newuserkk.naukatesting.domain.timekeeper.model.AttendanceStatus
import ru.newuserkk.naukatesting.domain.timekeeper.model.MarkedEmployee
import ru.newuserkk.naukatesting.presentation.presenter.common.AbstractItemAddPresenter
import ru.newuserkk.naukatesting.presentation.view.common.AbstractItemAddActivity
import ru.newuserkk.naukatesting.presentation.view.timekeeper.TimekeeperAddActivity
import kotlin.coroutines.CoroutineContext

class TimekeeperAddPresenter(override val view: TimekeeperAddActivity) :
    AbstractItemAddPresenter<MarkedEmployee>(view) {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private val calendarInteractor: CalendarInteractor = CalendarInteractorImpl()
    private val employeeInteractor: EmployeeInteractor = EmployeeInteractorImpl()

    override fun createItemFromOptions(options: AbstractItemAddActivity.ItemOptions): Result<MarkedEmployee> {
        options as TimekeeperAddActivity.MarkedEmployeeOptions
        return Result(MarkedEmployee(options.date, options.employee, options.status))
    }

    override suspend fun addItem(item: MarkedEmployee): Result<MarkedEmployee> {
        return calendarInteractor.addEmployeeMark(item)
    }

    override fun changeItemId(editingItem: MarkedEmployee, itemToAdd: MarkedEmployee) {
        editingItem.id = itemToAdd.id
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
