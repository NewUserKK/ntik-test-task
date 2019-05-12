package ru.newuserkk.timesheet.domain.timekeeper

import ru.newuserkk.timesheet.domain.timekeeper.model.MarkedEmployee
import ru.newuserkk.timesheet.domain.common.Result
import ru.newuserkk.timesheet.presentation.view.timekeeper.TimekeeperAddActivity
import java.util.*

interface CalendarInteractor {

    suspend fun getEmployeesByDate(date: Date): Result<List<MarkedEmployee>>
    suspend fun addEmployeeMark(employee: MarkedEmployee): Result<MarkedEmployee>
    suspend fun updateEmployeeMark(id: Long, newMark: MarkedEmployee): Result<MarkedEmployee>
    suspend fun removeEmployeeMark(employeeMark: MarkedEmployee): Result<MarkedEmployee>

    fun buildItemFromOptions(options: TimekeeperAddActivity.MarkedEmployeeOptions): Result<MarkedEmployee>

}