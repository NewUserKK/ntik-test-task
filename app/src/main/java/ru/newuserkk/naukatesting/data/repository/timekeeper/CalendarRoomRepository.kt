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

    override suspend fun addEmployeeMark(employeeMark: MarkedEmployee): MarkedEmployee? {
        Log.d(LOG_TAG, "Adding marked employee for date: ${employeeMark.date}")
        return employeeMark.apply { id = calendarDAO.add(employeeMark) }
    }

    override suspend fun updateEmployeeMark(id: Long, employee: MarkedEmployee): MarkedEmployee? {
        val itemToUpdate = employee.apply { this.id = id }
        calendarDAO.update(itemToUpdate)
        return itemToUpdate
    }

    override suspend fun removeEmployeeMark(markedEmployee: MarkedEmployee) {
        calendarDAO.remove(markedEmployee)
    }

    companion object {
        const val LOG_TAG = "CalendarRoomRepository"
    }

}