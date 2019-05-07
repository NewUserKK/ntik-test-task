package ru.newuserkk.naukatesting.data.repository.timekeeper

import android.util.Log
import ru.newuserkk.naukatesting.TimesheetApp
import ru.newuserkk.naukatesting.data.db.toEntity
import ru.newuserkk.naukatesting.data.db.toMarkedEmployee
import ru.newuserkk.naukatesting.domain.timekeeper.CalendarRepository
import ru.newuserkk.naukatesting.domain.timekeeper.model.MarkedEmployee
import java.util.*

class CalendarRepositoryImpl: CalendarRepository {

    private val calendarDAO = TimesheetApp.applicationDatabase.calendarDAO()

    override suspend fun getEmployeesByDate(date: Date): List<MarkedEmployee> {
        Log.d(LOG_TAG, "Fetching employees for date: $date")
        return calendarDAO.getEmployeesByDate(date).mapNotNull { it.toMarkedEmployee() }
    }

    override suspend fun addEmployeeMark(markedEmployee: MarkedEmployee): MarkedEmployee? {
        Log.d(LOG_TAG, "Adding marked employee for date: ${markedEmployee.date}")
        val addedId = calendarDAO.addMark(markedEmployee.toEntity())
        val addedEmployee = calendarDAO.getById(addedId)
        return addedEmployee.toMarkedEmployee()
    }

    companion object {
        const val LOG_TAG = "CalendarRepositoryImpl"
    }

}