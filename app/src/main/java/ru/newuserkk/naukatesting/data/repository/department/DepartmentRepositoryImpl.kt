package ru.newuserkk.naukatesting.data.repository.department

import ru.newuserkk.naukatesting.TimesheetApp
import ru.newuserkk.naukatesting.data.db.toDepartment
import ru.newuserkk.naukatesting.data.db.toEntity
import ru.newuserkk.naukatesting.domain.department.DepartmentRepository
import ru.newuserkk.naukatesting.domain.department.model.Department

class DepartmentRepositoryImpl : DepartmentRepository {

    private val departmentDao = TimesheetApp.applicationDatabase.departmentDAO()

    override suspend fun addDepartment(department: Department): Department? {
        val addedId = departmentDao.add(department.toEntity())
        return departmentDao.getDepartmentById(addedId).toDepartment()
    }

    override suspend fun getDepartments(): List<Department> {
        return departmentDao.getAll().mapNotNull { it.toDepartment() }
    }

    override suspend fun editDepartment(department: Department): Department? {
        val entity = department.toEntity()
        val updatedId = departmentDao.update(entity)
        val updatedDepartment = departmentDao.getDepartmentById(updatedId.toLong()).toDepartment()
        return updatedDepartment
    }
}