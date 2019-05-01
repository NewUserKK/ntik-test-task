package ru.newuserkk.naukatesting.data.repository.employee

import ru.newuserkk.naukatesting.domain.department.model.Department
import ru.newuserkk.naukatesting.domain.employee.EmployeeRepository
import ru.newuserkk.naukatesting.domain.employee.model.Address
import ru.newuserkk.naukatesting.domain.employee.model.Employee
import java.sql.Date

class EmployeeRepositoryImpl: EmployeeRepository {

    override suspend fun addEmployee(employee: Employee) {
        TODO("not implemented")
    }

    override suspend fun getEmployees(): List<Employee> {
        TODO("not implemented")
    }

}