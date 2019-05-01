package ru.newuserkk.naukatesting.presentation.presenter.timekeeper

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.newuserkk.naukatesting.data.repository.department.DepartmentRepositoryImpl
import ru.newuserkk.naukatesting.data.repository.department.DepartmentRepositoryTest
import ru.newuserkk.naukatesting.data.repository.employee.EmployeeRepositoryTest
import ru.newuserkk.naukatesting.domain.department.DepartmentRepository
import ru.newuserkk.naukatesting.domain.employee.model.Address
import ru.newuserkk.naukatesting.domain.department.model.Department
import ru.newuserkk.naukatesting.domain.employee.EmployeeRepository
import ru.newuserkk.naukatesting.domain.employee.model.Employee
import java.sql.Date
import kotlin.coroutines.CoroutineContext

class TimekeeperDetailPresenter: CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private val departmentRepository: DepartmentRepository = DepartmentRepositoryTest()
    private val employeeRepository: EmployeeRepository = EmployeeRepositoryTest()


    companion object {
        const val LOG_TAG = "TimekeeperDetailPresenter"
    }

    fun getEmployeesData(): List<Employee> {
        TODO()
    }

    fun getDepartmentData(): List<Department> {
        TODO()
    }
}