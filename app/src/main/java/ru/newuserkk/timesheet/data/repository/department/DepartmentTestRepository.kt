package ru.newuserkk.timesheet.data.repository.department

import ru.newuserkk.timesheet.domain.department.DepartmentRepository
import ru.newuserkk.timesheet.domain.department.model.Department

class DepartmentTestRepository: DepartmentRepository {

    private val departments = mutableListOf(
        Department("First"),
        Department("Second"),
        Department("Third"),
        Department("VeeeeeeeeeeeeeeeeeeeeeeeeeeeryLongNameBecauseICanDepartment"),
        Department("Something else")
    )

    companion object {
        const val LOG_TAG = "DepartmentTestRepository"
    }

    override suspend fun addDepartment(department: Department): Department? {
        Thread.sleep(2000)
        departments += department
        return department
    }

    override suspend fun getDepartments(): List<Department> {
        return departments
    }

    override suspend fun removeDepartment(department: Department) {
        departments.remove(department)
    }

    override suspend fun updateDepartment(id: Long, department: Department): Department? {
        TODO("not implemented")
    }
}