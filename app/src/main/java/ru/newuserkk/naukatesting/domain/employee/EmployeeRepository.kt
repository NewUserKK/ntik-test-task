package ru.newuserkk.naukatesting.domain.employee

import ru.newuserkk.naukatesting.domain.employee.model.Employee

interface EmployeeRepository {

    fun addEmployee(employee: Employee)

    fun getEmployees(): List<Employee>

}