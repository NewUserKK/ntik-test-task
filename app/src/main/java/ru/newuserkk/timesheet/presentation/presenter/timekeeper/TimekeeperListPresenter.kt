package ru.newuserkk.timesheet.presentation.presenter.timekeeper

import kotlinx.coroutines.Dispatchers
import ru.newuserkk.timesheet.domain.common.Result
import ru.newuserkk.timesheet.domain.timekeeper.CalendarInteractor
import ru.newuserkk.timesheet.domain.timekeeper.CalendarInteractorImpl
import ru.newuserkk.timesheet.domain.timekeeper.model.MarkedEmployee
import ru.newuserkk.timesheet.presentation.presenter.common.AbstractListPresenter
import ru.newuserkk.timesheet.presentation.view.timekeeper.TimekeeperListActivity
import java.util.*
import kotlin.coroutines.CoroutineContext

class TimekeeperListPresenter(view: TimekeeperListActivity, val date: Date) : AbstractListPresenter<MarkedEmployee>(view) {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private val calendarInteractor: CalendarInteractor = CalendarInteractorImpl()

    override suspend fun getItems(): Result<List<MarkedEmployee>> {
        return calendarInteractor.getEmployeesByDate(date)
    }

    override suspend fun removeItemImpl(item: MarkedEmployee): Result<MarkedEmployee> {
        return calendarInteractor.removeEmployeeMark(item)
    }
}