package ru.newuserkk.timesheet.domain.department

import ru.newuserkk.timesheet.domain.common.Result
import ru.newuserkk.timesheet.domain.department.model.Department

interface DepartmentInteractor {
    suspend fun addDepartment(department: Department): Result<Department>
    suspend fun getDepartments(): Result<List<Department>>
    suspend fun updateDepartment(id: Long, department: Department): Result<Department>
    suspend fun removeDepartment(department: Department): Result<Department>
}