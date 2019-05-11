package ru.newuserkk.naukatesting.data.repository.timekeeper

import android.util.Log
import ru.newuserkk.naukatesting.TimesheetApp
import ru.newuserkk.naukatesting.domain.timekeeper.CalendarRepository
import ru.newuserkk.naukatesting.domain.timekeeper.model.MarkedEmployee
import java.util.*

class CalendarRoomRepository : CalendarRepository {

    private val calendarDAO = TimesheetApp.applicationDatabase.calendarDAO()

    override suspend fun getEmployeesByDate(date: Date): List<MarkedEmployee> {
        Log.d(LOG_TAG, "Fetching employees for date: $date")
        val employees = calendarDAO.getEmployeesByDate(date)
        employees.forEach {
            it.employee = TimesheetApp.applicationDatabase
                .employeeDAO()
                .getById(it.employeeId)!!
        }
        return employees
    }

    override suspend fun addEmployeeMark(markedEmployee: MarkedEmployee): MarkedEmployee? {
        Log.d(LOG_TAG, "Adding marked employee for date: ${markedEmployee.date}")
        return markedEmployee.apply { id = calendarDAO.add(markedEmployee) }
    }

    companion object {
        const val LOG_TAG = "CalendarRoomRepository"
    }

}