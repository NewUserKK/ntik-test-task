package ru.newuserkk.naukatesting.domain.employee

import ru.newuserkk.naukatesting.domain.employee.model.Employee
import ru.newuserkk.naukatesting.domain.common.Result
import ru.newuserkk.naukatesting.presentation.view.employee.EmployeeAddActivity

interface EmployeeInteractor {

    suspend fun addEmployee(employee: Employee): Result<Employee>
    suspend fun getEmployees(): Result<List<Employee>>
    suspend fun removeEmployee(employee: Employee): Result<Employee>
    suspend fun updateEmployee(id: Long, employee: Employee): Result<Employee>

    fun buildEmployeeFromOptions(options: EmployeeAddActivity.EmployeeOptions): Result<Employee>

}