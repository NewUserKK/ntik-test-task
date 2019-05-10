package ru.newuserkk.naukatesting.data.repository.employee

import android.util.Log
import ru.newuserkk.naukatesting.TimesheetApp
import ru.newuserkk.naukatesting.data.db.toEmployee
import ru.newuserkk.naukatesting.data.db.toEntity
import ru.newuserkk.naukatesting.domain.employee.EmployeeRepository
import ru.newuserkk.naukatesting.domain.employee.model.Employee


class EmployeeRepositoryImpl : EmployeeRepository {

    private val employeeDAO = TimesheetApp.applicationDatabase.employeeDAO()
    private val addressDAO = TimesheetApp.applicationDatabase.addressDAO()

    override suspend fun addEmployee(employee: Employee): Employee? {
        Log.d(LOG_TAG, "Adding employee to db...")
        Log.d(LOG_TAG, "Adding address...")
        val addedAddressId = addressDAO.add(
            employee.address.toEntity()
        )

        Log.d(LOG_TAG, "Adding employee...")
        val addedEmployeeId = employeeDAO.add(
            employee.toEntity() ?: return null
        )

        Log.d(LOG_TAG, "Updating address' employee id")
        addressDAO.updateEmployeeId(addedAddressId, addedEmployeeId)

        Log.d(LOG_TAG, "Done")
        return employeeDAO.getById(addedEmployeeId).toEmployee()
    }

    override suspend fun getEmployees(): List<Employee> {
        Log.d(LOG_TAG, "Fetching employees from db...")
        return employeeDAO.getAll().mapNotNull{ it.toEmployee() }
    }

    override suspend fun editEmployee(employee: Employee): Employee? {
        TODO()
    }

    companion object {
        const val LOG_TAG = "EmployeeRepositoryImpl"
    }
}