package ru.newuserkk.naukatesting.data.repository.employee

import android.util.Log
import ru.newuserkk.naukatesting.TimesheetApp
import ru.newuserkk.naukatesting.domain.employee.EmployeeRepository
import ru.newuserkk.naukatesting.domain.employee.model.Employee
import java.io.IOException


class EmployeeRoomRepository : EmployeeRepository {

    private val employeeDAO = TimesheetApp.applicationDatabase.employeeDAO()
//    TODO: private val addressDAO = TimesheetApp.applicationDatabase.addressDAO()

    override suspend fun addEmployee(employee: Employee): Employee? {
        Log.d(LOG_TAG, "Adding employee to db...")
//        val addressId = addressDAO.add(employee.address)
//        employee.address?.id = addressId ?: throw IOException("Couldn't add address ${employee.address}!")

        val employeeId = employeeDAO.add(employee)
        employee.id = employeeId

        return employee

    }

    override suspend fun getEmployees(): List<Employee> {
        Log.d(LOG_TAG, "Fetching employees from db...")
        return employeeDAO.getAll()
    }

    companion object {
        const val LOG_TAG = "EmployeeRoomRepository"
    }
}