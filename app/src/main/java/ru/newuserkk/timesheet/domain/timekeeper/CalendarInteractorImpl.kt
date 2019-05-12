package ru.newuserkk.timesheet.domain.timekeeper

import android.util.Log
import ru.newuserkk.timesheet.data.repository.timekeeper.CalendarRoomRepository
import ru.newuserkk.timesheet.domain.common.Result
import ru.newuserkk.timesheet.domain.timekeeper.model.MarkedEmployee
import ru.newuserkk.timesheet.presentation.view.timekeeper.TimekeeperAddActivity
import java.util.*

class CalendarInteractorImpl: CalendarInteractor {

    private val calendarRepository: CalendarRepository = CalendarRoomRepository()

    override suspend fun getEmployeesByDate(date: Date): Result<List<MarkedEmployee>> {
        return try {
            val result = calendarRepository.getEmployeesByDate(date)
            Result(result, null)
        } catch (e: Throwable) {
            Log.e(LOG_TAG, e.message)
            Result(null, e)
        }
    }

    override suspend fun addEmployeeMark(employee: MarkedEmployee): Result<MarkedEmployee> {
        return try {
            Result(calendarRepository.addEmployeeMark(employee), null)
        } catch (e: Throwable) {
            Log.e(LOG_TAG, e.message)
            Result(null, e)
        }
    }

    override suspend fun updateEmployeeMark(id: Long, newMark: MarkedEmployee): Result<MarkedEmployee> {
        return try {
            Result(calendarRepository.updateEmployeeMark(id, newMark))
        } catch (e: Throwable) {
            Log.e(LOG_TAG, e.message)
            Result(null, e)
        }
    }

    override suspend fun removeEmployeeMark(employeeMark: MarkedEmployee): Result<MarkedEmployee> {
        return try {
            calendarRepository.removeEmployeeMark(employeeMark)
            Result(null)
        } catch (e: Throwable) {
            Log.e(LOG_TAG, e.message)
            Result(null, e)
        }
    }

    override fun buildItemFromOptions(options: TimekeeperAddActivity.MarkedEmployeeOptions): Result<MarkedEmployee> {
        if (options.employee == null) {
            return Result(null, IllegalArgumentException("No employee is present"))
        }

        return Result(
            MarkedEmployee(options.date, options.employee.id!!, options.status).apply {
                employee = options.employee
            }
        )
    }

    companion object {
        const val LOG_TAG = "CalendarInteractorImpl"
    }

}