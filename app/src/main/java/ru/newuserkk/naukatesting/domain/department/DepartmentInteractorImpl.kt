package ru.newuserkk.naukatesting.domain.department

import android.util.Log
import ru.newuserkk.naukatesting.data.repository.department.DepartmentRepositoryImpl
import ru.newuserkk.naukatesting.domain.common.Result
import ru.newuserkk.naukatesting.domain.department.model.Department

class DepartmentInteractorImpl: DepartmentInteractor {

    private val repository: DepartmentRepository = DepartmentRepositoryImpl()

    override suspend fun addDepartment(department: Department): Result<Department> {
        return try {
            Result(repository.addDepartment(department), null)
        } catch (e: Throwable) {
            Log.e(LOG_TAG, e.message)
            Result(null, e)
        }
    }

    override suspend fun getDepartments(): Result<List<Department>> {
        return try {
            Result(repository.getDepartments(), null)
        } catch (e: Throwable) {
            Log.e(LOG_TAG, e.message)
            Result(null, e)
        }
    }

    companion object {
        const val LOG_TAG = "DepartmentInteractor"
    }
}