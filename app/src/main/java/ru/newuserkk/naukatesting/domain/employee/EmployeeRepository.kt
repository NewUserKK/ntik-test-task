package ru.newuserkk.naukatesting.domain.employee

import ru.newuserkk.naukatesting.domain.employee.model.Employee

interface EmployeeRepository {

    suspend fun addEmployee(employee: Employee): Employee?
    suspend fun getEmployees(): List<Employee>
    suspend fun updateEmployee(id: Long, employee: Employee): Employee?
    suspend fun removeEmployee(employee: Employee)
}