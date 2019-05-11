package ru.newuserkk.naukatesting.data.repository.department

import ru.newuserkk.naukatesting.TimesheetApp
import ru.newuserkk.naukatesting.domain.department.DepartmentRepository
import ru.newuserkk.naukatesting.domain.department.model.Department

class DepartmentRepositoryImpl : DepartmentRepository {

    private val departmentDAO = TimesheetApp.applicationDatabase.departmentDAO()

    override suspend fun addDepartment(department: Department): Department? {
        return department.apply { id = departmentDAO.add(department) }
    }

    override suspend fun getDepartments(): List<Department> {
        return departmentDAO.getAll()
    }

    companion object {
        const val LOG_TAG = "DepartmentRepository"
    }
}