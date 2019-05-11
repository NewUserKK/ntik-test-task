package ru.newuserkk.naukatesting.data.repository.timekeeper

import android.util.Log
import ru.newuserkk.naukatesting.TimesheetApp
import ru.newuserkk.naukatesting.domain.timekeeper.CalendarRepository
import ru.newuserkk.naukatesting.domain.timekeeper.model.MarkedEmployee
import java.util.*

class CalendarRepositoryImpl: CalendarRepository {

    private val calendarDAO = TimesheetApp.applicationDatabase.calendarDAO()

    override suspend fun getEmployeesByDate(date: Date): List<MarkedEmployee> {
        Log.d(LOG_TAG, "Fetching employees for date: $date")
        return calendarDAO.getEmployeesByDate(date)
    }

    override suspend fun addEmployeeMark(markedEmployee: MarkedEmployee): MarkedEmployee? {
        Log.d(LOG_TAG, "Adding marked employee for date: ${markedEmployee.date}")
        val addedId = calendarDAO.addMark(markedEmployee)
        val addedEmployee = calendarDAO.getById(addedId)
        return addedEmployee
    }

    companion object {
        const val LOG_TAG = "CalendarRepositoryImpl"
    }

}