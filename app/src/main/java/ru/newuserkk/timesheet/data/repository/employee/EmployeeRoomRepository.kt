package ru.newuserkk.timesheet.data.repository.employee

import android.util.Log
import ru.newuserkk.timesheet.TimesheetApp
import ru.newuserkk.timesheet.domain.employee.EmployeeRepository
import ru.newuserkk.timesheet.domain.employee.model.Employee


class EmployeeRoomRepository : EmployeeRepository {

    private val employeeDAO = TimesheetApp.applicationDatabase.employeeDAO()

    override suspend fun addEmployee(employee: Employee): Employee? {
        Log.d(LOG_TAG, "Adding employee to db...")

        val employeeId = employeeDAO.add(employee)
        employee.id = employeeId

        Log.d(LOG_TAG, "OK")
        return employee

    }

    override suspend fun getEmployees(): List<Employee> {
        Log.d(LOG_TAG, "Fetching employees from db...")
        return employeeDAO.getAll()
    }

    override suspend fun updateEmployee(id: Long, employee: Employee): Employee? {
        val itemToUpdate = employee.apply { this.id = id }
        employeeDAO.update(itemToUpdate)
        return itemToUpdate
    }

    override suspend fun removeEmployee(employee: Employee) {
        employee.deleted = true
        employeeDAO.update(employee)
//        employeeDAO.remove(employee)
    }

    companion object {
        const val LOG_TAG = "EmployeeRoomRepository"
    }
}