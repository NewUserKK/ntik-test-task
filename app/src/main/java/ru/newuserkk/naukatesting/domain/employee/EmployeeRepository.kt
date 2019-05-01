package ru.newuserkk.naukatesting.domain.employee

import ru.newuserkk.naukatesting.domain.employee.model.Employee

interface EmployeeRepository {

    suspend fun addEmployee(employee: Employee)

    suspend fun getEmployees(): List<Employee>

}