package ru.newuserkk.timesheet.presentation.presenter.timekeeper

import kotlinx.coroutines.Dispatchers
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance
import ru.newuserkk.timesheet.TimesheetApp
import ru.newuserkk.timesheet.domain.common.Result
import ru.newuserkk.timesheet.domain.timekeeper.CalendarInteractor
import ru.newuserkk.timesheet.domain.timekeeper.CalendarInteractorImpl
import ru.newuserkk.timesheet.domain.timekeeper.model.MarkedEmployee
import ru.newuserkk.timesheet.presentation.presenter.common.AbstractListPresenter
import ru.newuserkk.timesheet.presentation.view.timekeeper.TimekeeperListActivity
import java.util.*
import kotlin.coroutines.CoroutineContext

class TimekeeperListPresenter(view: TimekeeperListActivity, val date: Date) :
    AbstractListPresenter<MarkedEmployee>(view), KodeinAware {

    override val kodein by lazy { TimesheetApp.kodein }

    private val calendarInteractor: CalendarInteractor by kodein.instance()

    override suspend fun getItems(): Result<List<MarkedEmployee>> {
        return calendarInteractor.getEmployeesByDate(date)
    }

    override suspend fun removeItemImpl(item: MarkedEmployee): Result<MarkedEmployee> {
        return calendarInteractor.removeEmployeeMark(item)
    }
}