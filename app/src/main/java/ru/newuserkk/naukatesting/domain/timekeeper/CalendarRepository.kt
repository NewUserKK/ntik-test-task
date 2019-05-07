package ru.newuserkk.naukatesting.domain.timekeeper

import ru.newuserkk.naukatesting.domain.employee.model.Employee
import ru.newuserkk.naukatesting.domain.timekeeper.model.MarkedEmployee
import java.util.*

interface CalendarRepository {

    suspend fun getEmployeesByDate(date: Date): List<MarkedEmployee>

    suspend fun addEmployeeMark(markedEmployee: MarkedEmployee): MarkedEmployee?

}