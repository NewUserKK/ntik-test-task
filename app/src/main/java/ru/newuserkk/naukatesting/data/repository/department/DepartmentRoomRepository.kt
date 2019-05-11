package ru.newuserkk.naukatesting.data.repository.department

import ru.newuserkk.naukatesting.TimesheetApp
import ru.newuserkk.naukatesting.domain.department.DepartmentRepository
import ru.newuserkk.naukatesting.domain.department.model.Department

class DepartmentRoomRepository : DepartmentRepository {

    private val departmentDAO = TimesheetApp.applicationDatabase.departmentDAO()

    override suspend fun addDepartment(department: Department): Department? {
        return department.apply { id = departmentDAO.add(department) }
    }

    override suspend fun getDepartments(): List<Department> {
        return departmentDAO.getAll()
    }

    override suspend fun updateDepartment(id: Long, department: Department): Department? {
        val itemToUpdate = department.apply { this.id = id }
        departmentDAO.update(itemToUpdate)
        return itemToUpdate
    }

    override suspend fun removeDepartment(department: Department) {
        department.deleted = true
        departmentDAO.update(department)
//        departmentDAO.remove(department)
    }

    companion object {
        const val LOG_TAG = "DepartmentRepository"
    }
}