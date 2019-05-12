package ru.newuserkk.timesheet.domain.employee

import ru.newuserkk.timesheet.domain.common.Result
import ru.newuserkk.timesheet.domain.employee.model.Employee
import ru.newuserkk.timesheet.presentation.view.employee.EmployeeAddActivity

interface EmployeeInteractor {

    suspend fun addEmployee(employee: Employee): Result<Employee>
    suspend fun getEmployees(): Result<List<Employee>>
    suspend fun removeEmployee(employee: Employee): Result<Employee>
    suspend fun updateEmployee(id: Long, employee: Employee): Result<Employee>

    fun buildEmployeeFromOptions(options: EmployeeAddActivity.EmployeeOptions): Result<Employee>

}