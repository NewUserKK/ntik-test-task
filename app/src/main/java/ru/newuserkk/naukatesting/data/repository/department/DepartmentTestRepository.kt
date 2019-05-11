package ru.newuserkk.naukatesting.data.repository.department

import ru.newuserkk.naukatesting.domain.department.DepartmentRepository
import ru.newuserkk.naukatesting.domain.department.model.Department

class DepartmentTestRepository: DepartmentRepository {

    companion object {
        const val LOG_TAG = "DepartmentTestRepository"
    }

    override suspend fun addDepartment(department: Department): Department? {
        Thread.sleep(2000)
        println("Add departmentId: $department")
        return department
    }

    override suspend fun getDepartments(): List<Department> {
        return listOf(
            Department("First"),
            Department("Second"),
            Department("Third"),
            Department("VeeeeeeeeeeeeeeeeeeeeeeeeeeeryLongNameBecauseICanDepartment"),
            Department("Something else"),
            Department("Каво"),
            Department("Чево")
        )
    }
}