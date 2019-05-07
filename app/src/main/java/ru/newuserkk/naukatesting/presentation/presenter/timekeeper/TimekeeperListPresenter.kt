package ru.newuserkk.naukatesting.presentation.presenter.timekeeper

import kotlinx.coroutines.Dispatchers
import ru.newuserkk.naukatesting.domain.common.Result
import ru.newuserkk.naukatesting.domain.timekeeper.CalendarInteractor
import ru.newuserkk.naukatesting.domain.timekeeper.CalendarInteractorImpl
import ru.newuserkk.naukatesting.domain.timekeeper.model.MarkedEmployee
import ru.newuserkk.naukatesting.presentation.presenter.common.AbstractListPresenter
import ru.newuserkk.naukatesting.presentation.view.timekeeper.TimekeeperListActivity
import java.util.*
import kotlin.coroutines.CoroutineContext

class TimekeeperListPresenter(view: TimekeeperListActivity, val date: Date) : AbstractListPresenter<MarkedEmployee>(view) {

    private val calendarInteractor: CalendarInteractor = CalendarInteractorImpl()

    override suspend fun getItems(): Result<List<MarkedEmployee>> {
        return calendarInteractor.getEmployeesByDate(date)
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main
}