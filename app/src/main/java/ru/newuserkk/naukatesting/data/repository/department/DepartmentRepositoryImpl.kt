package ru.newuserkk.naukatesting.data.repository.department

import ru.newuserkk.naukatesting.TimesheetApp
import ru.newuserkk.naukatesting.data.db.Converters
import ru.newuserkk.naukatesting.data.db.department.DepartmentEntity
import ru.newuserkk.naukatesting.domain.department.DepartmentRepository
import ru.newuserkk.naukatesting.domain.department.model.Department

class DepartmentRepositoryImpl: DepartmentRepository {

    private val departmentDatabase = TimesheetApp.applicationDatabase

    override suspend fun addDepartment(department: Department) {
        departmentDatabase.departmentDAO().add(Converters.departmentToEntity(department))
    }

    override suspend fun getDepartments(): List<Department> {
        return departmentDatabase.departmentDAO().getAll().mapNotNull(Converters::departmentFromEntity)
    }
}