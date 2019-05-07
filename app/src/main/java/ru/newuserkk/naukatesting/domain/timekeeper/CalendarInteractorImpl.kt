package ru.newuserkk.naukatesting.domain.timekeeper

import android.util.Log
import ru.newuserkk.naukatesting.data.repository.timekeeper.CalendarRepositoryImpl
import ru.newuserkk.naukatesting.domain.common.Result
import ru.newuserkk.naukatesting.domain.timekeeper.model.MarkedEmployee
import java.util.*

class CalendarInteractorImpl: CalendarInteractor {

    private val calendarRepository: CalendarRepository = CalendarRepositoryImpl()

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

    companion object {
        const val LOG_TAG = "CalendarInteractorImpl"
    }

}