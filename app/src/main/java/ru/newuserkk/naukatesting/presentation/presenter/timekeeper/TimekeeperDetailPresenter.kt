package ru.newuserkk.naukatesting.presentation.presenter.timekeeper

import ru.newuserkk.naukatesting.domain.department.DepartmentRepository
import ru.newuserkk.naukatesting.domain.employee.model.Address
import ru.newuserkk.naukatesting.domain.department.model.Department
import ru.newuserkk.naukatesting.domain.employee.EmployeeRepository
import ru.newuserkk.naukatesting.domain.employee.model.Employee
import java.sql.Date

class TimekeeperDetailPresenter {

    private val departmentRepository: DepartmentRepository = TODO()
    private val employeeRepository: EmployeeRepository = TODO()


    companion object {
        const val LOG_TAG = "TimekeeperDetailPresenter"
    }

    fun getEmployeesData(): List<Employee> {
        return employeeRepository.getEmployees()
    }

    private fun getRandomEmployee(): Employee {
        return Employee(
            firstName = getRandomString(15),
            lastName = getRandomString(15),
            middleName = getRandomString(15),
            birthDate = Date.valueOf("2019-04-28"),
            department = Department("Department"),
            position = "Android",
            address = Address("Russia", "SPB", "1", "@", "3")
        )
    }

    fun getRandomString(length: Int) : String {
        val allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz"
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }
}