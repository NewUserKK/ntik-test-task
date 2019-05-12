package ru.newuserkk.timesheet.presentation.presenter.department

import kotlinx.coroutines.Dispatchers
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance
import ru.newuserkk.timesheet.TimesheetApp
import ru.newuserkk.timesheet.domain.common.Result
import ru.newuserkk.timesheet.domain.department.DepartmentInteractor
import ru.newuserkk.timesheet.domain.department.DepartmentInteractorImpl
import ru.newuserkk.timesheet.domain.department.model.Department
import ru.newuserkk.timesheet.presentation.presenter.common.AbstractListPresenter
import ru.newuserkk.timesheet.presentation.view.department.DepartmentListActivity
import kotlin.coroutines.CoroutineContext

class DepartmentListPresenter(view: DepartmentListActivity) :
    AbstractListPresenter<Department>(view), KodeinAware {

    override val kodein by lazy { TimesheetApp.kodein }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main


    private val interactor: DepartmentInteractor by kodein.instance()

    override suspend fun getItems(): Result<List<Department>> {
        return interactor.getDepartments()
    }

    override suspend fun removeItemImpl(item: Department): Result<Department> {
        return interactor.removeDepartment(item)
    }
}