package ru.newuserkk.naukatesting.domain.department

import android.util.Log
import ru.newuserkk.naukatesting.data.repository.department.DepartmentRoomRepository
import ru.newuserkk.naukatesting.domain.common.Result
import ru.newuserkk.naukatesting.domain.department.model.Department

class DepartmentInteractorImpl: DepartmentInteractor {

    private val repository: DepartmentRepository = DepartmentRoomRepository()

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

    override suspend fun updateDepartment(id: Long, department: Department): Result<Department> {
        return try {
            Result(repository.updateDepartment(id, department))
        } catch (e: Throwable) {
            Log.e(LOG_TAG, e.message)
            Result(null, e)
        }
    }

    override suspend fun removeDepartment(department: Department): Result<Department> {
        return try {
            repository.removeDepartment(department)
            Result(null)
        } catch (e: Throwable) {
            Log.e(LOG_TAG, e.message)
            Result(null, e)
        }
    }

    companion object {
        const val LOG_TAG = "DepartmentInteractor"
    }
}