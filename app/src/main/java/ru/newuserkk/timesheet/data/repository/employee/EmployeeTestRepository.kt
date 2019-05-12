package ru.newuserkk.timesheet.data.repository.employee

import ru.newuserkk.timesheet.domain.employee.EmployeeRepository
import ru.newuserkk.timesheet.domain.employee.model.Address
import ru.newuserkk.timesheet.domain.employee.model.Employee
import java.sql.Date

class EmployeeTestRepository: EmployeeRepository {

    private val employeeList = mutableListOf<Employee>()

    init {
        repeat(15) {
            employeeList += getRandomEmployee()
        }
    }

    override suspend fun addEmployee(employee: Employee): Employee? {
        employeeList.add(employee)
        return employee
    }

    override suspend fun getEmployees(): List<Employee> {
        return employeeList
    }

    fun getRandomEmployee(): Employee {
        return Employee(
            firstName = getRandomString(15),
            lastName = getRandomString(15),
            middleName = getRandomString(15),
            birthDate = Date.valueOf("2019-04-28"),
            departmentId = 0,
            position = "Android",
            address = Address("Russia", "SPB", "1", "3", "3"),
            phone = "88005553535"
        )
    }

    fun getRandomString(length: Int) : String {
        val allowedChars = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ"
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }

    override suspend fun updateEmployee(id: Long, employee: Employee): Employee? {
        TODO("not implemented")
    }

    override suspend fun removeEmployee(employee: Employee) {
        employeeList.remove(employee)
    }
}