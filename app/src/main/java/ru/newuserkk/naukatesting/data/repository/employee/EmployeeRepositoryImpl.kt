package ru.newuserkk.naukatesting.data.repository.employee

import android.util.Log
import ru.newuserkk.naukatesting.TimesheetApp
import ru.newuserkk.naukatesting.domain.employee.EmployeeRepository
import ru.newuserkk.naukatesting.domain.employee.model.Employee


class EmployeeRepositoryImpl : EmployeeRepository {

    private val employeeDAO = TimesheetApp.applicationDatabase.employeeDAO()
    private val addressDAO = TimesheetApp.applicationDatabase.addressDAO()

    override suspend fun addEmployee(employee: Employee): Employee? {
        Log.d(LOG_TAG, "Adding employee to db...")
//        employee.address?.employee = employee
//        employee.address?.id = addressDAO.add(employee.address).toInt()
//        return employeeDAO.getById(employeeDAO.add(employee))
        val addressId = addressDAO.add(employee.address)
        employee.address?.id = addressId ?: return null

        val employeeId = employeeDAO.add(employee)
        employee.id = employeeId

        addressDAO.updateEmployee(addressId, employee)
        employee.address?.employee = employee

        return employee

    }

    override suspend fun getEmployees(): List<Employee> {
        Log.d(LOG_TAG, "Fetching employees from db...")
        return employeeDAO.getAll()
    }

    override suspend fun editEmployee(employee: Employee): Employee? {
        TODO()
    }

    companion object {
        const val LOG_TAG = "EmployeeRepositoryImpl"
    }
}