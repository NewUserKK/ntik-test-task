package ru.newuserkk.naukatesting.data.repository.employee

import ru.newuserkk.naukatesting.domain.department.model.Department
import ru.newuserkk.naukatesting.domain.employee.EmployeeRepository
import ru.newuserkk.naukatesting.domain.employee.model.Address
import ru.newuserkk.naukatesting.domain.employee.model.Employee
import java.sql.Date

class EmployeeRepositoryTest: EmployeeRepository {

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

    override suspend fun editEmployee(employee: Employee): Employee? {
        return employee
    }

    fun getRandomEmployee(): Employee {
        return Employee(
            firstName = getRandomString(15),
            lastName = getRandomString(15),
            middleName = getRandomString(15),
            birthDate = Date.valueOf("2019-04-28"),
            department = Department("Department"),
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
}