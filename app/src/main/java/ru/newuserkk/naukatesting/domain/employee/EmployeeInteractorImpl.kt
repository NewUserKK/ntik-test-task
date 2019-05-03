package ru.newuserkk.naukatesting.domain.employee

import ru.newuserkk.naukatesting.data.repository.employee.EmployeeRepositoryImpl
import ru.newuserkk.naukatesting.data.repository.employee.EmployeeRepositoryTest
import ru.newuserkk.naukatesting.domain.common.Result
import ru.newuserkk.naukatesting.domain.employee.model.Address
import ru.newuserkk.naukatesting.domain.employee.model.Employee
import ru.newuserkk.naukatesting.presentation.view.employee.EmployeeAddActivity
import java.text.SimpleDateFormat

class EmployeeInteractorImpl : EmployeeInteractor {

    private val repository: EmployeeRepository = EmployeeRepositoryImpl()

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
        // TODO: validation
        return Result(
            options.run {
                Employee(
                    firstName = firstName,
                    lastName = lastName,
                    middleName = middleName,
                    birthDate = SimpleDateFormat("dd.MM.yyyy").parse(birthDate),
                    department = department,
                    position = position,
                    address = Address(country, city, street, house, flat),
                    phone = phone
                )
            }
            , null)
    }
}