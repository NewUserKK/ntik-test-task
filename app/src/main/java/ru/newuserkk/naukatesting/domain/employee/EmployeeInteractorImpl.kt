package ru.newuserkk.naukatesting.domain.employee

import ru.newuserkk.naukatesting.data.repository.employee.EmployeeRoomRepository
import ru.newuserkk.naukatesting.domain.common.Result
import ru.newuserkk.naukatesting.domain.employee.model.Address
import ru.newuserkk.naukatesting.domain.employee.model.Employee
import ru.newuserkk.naukatesting.presentation.view.employee.EmployeeAddActivity
import java.lang.IllegalArgumentException
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
            e.printStackTrace()
            Result(null, e)
        }
    }

    override suspend fun getEmployees(): Result<List<Employee>> {
        return try {
            Result(repository.getEmployees(), null)
        } catch (e: Throwable) {
            e.printStackTrace()
            Result(null, e)
        }
    }

    override fun buildEmployee(options: EmployeeAddActivity.EmployeeOptions): Result<Employee> {
        if (options.department == null) {
            return Result(null, IllegalArgumentException("No departmentId is present"))
        }
        return try {
            Result(
                options.run {
                    Employee(
                        firstName = firstName,
                        lastName = lastName,
                        middleName = middleName,
                        birthDate = SimpleDateFormat("dd.MM.yyyy", Locale.US).parse(birthDate),
                        departmentId = department!!.id,
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