package ru.newuserkk.naukatesting.domain.department

import ru.newuserkk.naukatesting.domain.common.Result
import ru.newuserkk.naukatesting.domain.department.model.Department

interface DepartmentInteractor {
    suspend fun addDepartment(department: Department): Result<Department>
    suspend fun getDepartments(): Result<List<Department>>
    suspend fun editDepartment(department: Department): Result<Department>
}