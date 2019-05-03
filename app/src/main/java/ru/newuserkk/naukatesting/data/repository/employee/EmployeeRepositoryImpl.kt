package ru.newuserkk.naukatesting.data.repository.employee

import android.util.Log
import ru.newuserkk.naukatesting.TimesheetApp
import ru.newuserkk.naukatesting.data.db.Converters
import ru.newuserkk.naukatesting.data.db.department.DepartmentEntity
import ru.newuserkk.naukatesting.data.db.employee.AddressEntity
import ru.newuserkk.naukatesting.data.db.employee.EmployeeEntity
import ru.newuserkk.naukatesting.domain.department.model.Department
import ru.newuserkk.naukatesting.domain.employee.EmployeeRepository
import ru.newuserkk.naukatesting.domain.employee.model.Address
import ru.newuserkk.naukatesting.domain.employee.model.Employee


class EmployeeRepositoryImpl : EmployeeRepository {

    private val employeeDAO = TimesheetApp.applicationDatabase.employeeDAO()
    private val addressDAO = TimesheetApp.applicationDatabase.addressDAO()

    override suspend fun addEmployee(employee: Employee): Employee? {
        Log.d(LOG_TAG, "Adding employee to db...")
        Log.d(LOG_TAG, "Adding address...")
        val addedAddressId = addressDAO.addAddress(
            Converters.addressToEntity(employee.address)
        )

        Log.d(LOG_TAG, "Adding employee...")
        val addedEmployeeId = employeeDAO.addEmployee(
            Converters.employeeToEntity(employee) ?: return null
        )

        Log.d(LOG_TAG, "Updating address' employee id")
        addressDAO.updateEmployeeId(addedAddressId, addedEmployeeId)

        Log.d(LOG_TAG, "Done")
        return Converters.employeeFromEntity(
            employeeDAO.getById(addedEmployeeId)
        )
    }

    override suspend fun getEmployees(): List<Employee> {
        Log.d(LOG_TAG, "Fetching employees from db...")
        return employeeDAO.getAll().mapNotNull(Converters::employeeFromEntity)
    }

    companion object {
        const val LOG_TAG = "EmployeeRepositoryImpl"
    }
}