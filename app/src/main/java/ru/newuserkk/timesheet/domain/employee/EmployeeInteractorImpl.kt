package ru.newuserkk.timesheet.domain.employee

import android.util.Log
import ru.newuserkk.timesheet.data.repository.employee.EmployeeRoomRepository
import ru.newuserkk.timesheet.domain.common.Result
import ru.newuserkk.timesheet.domain.employee.model.Address
import ru.newuserkk.timesheet.domain.employee.model.Employee
import ru.newuserkk.timesheet.presentation.view.employee.EmployeeAddActivity
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class EmployeeInteractorImpl : EmployeeInteractor {

    private val repository: EmployeeRepository = EmployeeRoomRepository()

    override suspend fun addEmployee(employee: Employee): Result<Employee> {
        return try {
            val result = repository.addEmployee(employee)
            Result(result, null)
        } catch (e: Throwable) {
            Log.e(LOG_TAG, e.message)
            Result(null, e)
        }
    }

    override suspend fun getEmployees(): Result<List<Employee>> {
        return try {
            Result(repository.getEmployees(), null)
        } catch (e: Throwable) {
            Log.e(LOG_TAG, e.message)
            Result(null, e)
        }
    }

    override suspend fun updateEmployee(id: Long, employee: Employee): Result<Employee> {
        return try {
            Result(repository.updateEmployee(id, employee))
        } catch (e: Throwable) {
            Log.e(LOG_TAG, e.message)
            Result(null, e)
        }
    }

    override suspend fun removeEmployee(employee: Employee): Result<Employee> {
        return try {
            repository.removeEmployee(employee)
            Result(null)
        } catch (e: Throwable) {
            Log.e(LOG_TAG, e.message)
            Result(null, e)
        }
    }

    override fun buildEmployeeFromOptions(options: EmployeeAddActivity.EmployeeOptions): Result<Employee> {
        if (options.department == null) {
            return Result(null, IllegalArgumentException("No department is present"))
        }
        return try {
            Result(
                options.run {
                    Employee(
                        firstName = firstName,
                        lastName = lastName,
                        middleName = middleName,
                        birthDate = SimpleDateFormat("dd.MM.yyyy", Locale.US).parse(birthDate),
                        departmentId = department!!.id!!,
                        position = position,
                        address = Address(country, city, street, house, flat),
                        phone = phone
                    )
                }
            )
        } catch (e: ParseException) {
            Result(null, IllegalArgumentException("Couldn't parse date! Required format: dd.mm.yyyy"))
        }
    }

    companion object {
        const val LOG_TAG = "EmployeeInteractorImpl"
    }
}