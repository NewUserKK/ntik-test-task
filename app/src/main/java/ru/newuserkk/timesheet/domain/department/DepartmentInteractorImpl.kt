package ru.newuserkk.timesheet.domain.department

import android.util.Log
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance
import ru.newuserkk.timesheet.TimesheetApp
import ru.newuserkk.timesheet.data.repository.department.DepartmentRoomRepository
import ru.newuserkk.timesheet.domain.common.Result
import ru.newuserkk.timesheet.domain.department.model.Department

class DepartmentInteractorImpl: DepartmentInteractor, KodeinAware {

    override val kodein by lazy { TimesheetApp.kodein }

    private val repository: DepartmentRepository by kodein.instance()

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