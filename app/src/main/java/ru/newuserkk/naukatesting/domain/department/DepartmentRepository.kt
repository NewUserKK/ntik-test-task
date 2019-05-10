package ru.newuserkk.naukatesting.domain.department

import ru.newuserkk.naukatesting.domain.department.model.Department

interface DepartmentRepository {

    suspend fun addDepartment(department: Department): Department?
    suspend fun getDepartments(): List<Department>
    suspend fun editDepartment(department: Department): Department?

}