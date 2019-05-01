package ru.newuserkk.naukatesting.data.repository.department

import ru.newuserkk.naukatesting.domain.department.DepartmentRepository
import ru.newuserkk.naukatesting.domain.department.model.Department

class DepartmentRepositoryTest: DepartmentRepository {

    companion object {
        const val LOG_TAG = "DepartmentRepositoryTest"
    }

    override suspend fun addDepartment(department: Department) {
        Thread.sleep(2000)
        println("Add department: $department")
    }

    override suspend fun getDepartments(): List<Department> {
        return listOf(
            Department("First"),
            Department("Second"),
            Department("Third"),
            Department("VeeeeeeeeeeeeeeryLongNameBecauseICanDepartment"),
            Department("Something else"),
            Department("Каво"),
            Department("Чево")
        )
    }
}