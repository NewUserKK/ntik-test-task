package ru.newuserkk.naukatesting.domain.timekeeper

import ru.newuserkk.naukatesting.domain.timekeeper.model.MarkedEmployee
import ru.newuserkk.naukatesting.domain.common.Result
import java.util.*

interface CalendarInteractor {

    suspend fun getEmployeesByDate(date: Date): Result<List<MarkedEmployee>>

    suspend fun addEmployeeMark(employee: MarkedEmployee): Result<MarkedEmployee>

}