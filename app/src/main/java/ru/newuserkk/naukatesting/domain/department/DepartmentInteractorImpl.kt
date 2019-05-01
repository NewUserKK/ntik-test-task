package ru.newuserkk.naukatesting.domain.department

import ru.newuserkk.naukatesting.data.repository.department.DepartmentRepositoryTest
import ru.newuserkk.naukatesting.domain.common.Result
import ru.newuserkk.naukatesting.domain.department.model.Department

class DepartmentInteractorImpl: DepartmentInteractor {

    private val repository: DepartmentRepository = DepartmentRepositoryTest()

    override suspend fun addDepartment(department: Department): Result<Department> {
        TODO("not implemented")
    }

    override suspend fun getDepartments(): Result<List<Department>> {
        return try {
            Result(repository.getDepartments(), null)
        } catch (e: Throwable) {
            Result(null, e)
        }
    }
}