package ru.newuserkk.naukatesting.data.db

import kotlinx.coroutines.runBlocking
import ru.newuserkk.naukatesting.TimesheetApp
import ru.newuserkk.naukatesting.data.db.department.DepartmentEntity
import ru.newuserkk.naukatesting.data.db.employee.AddressEntity
import ru.newuserkk.naukatesting.data.db.employee.EmployeeEntity
import ru.newuserkk.naukatesting.data.db.timekeeper.MarkedEmployeeEntity
import ru.newuserkk.naukatesting.domain.department.model.Department
import ru.newuserkk.naukatesting.domain.employee.model.Address
import ru.newuserkk.naukatesting.domain.employee.model.Employee
import ru.newuserkk.naukatesting.domain.timekeeper.model.MarkedEmployee

private val employeeDAO = TimesheetApp.applicationDatabase.employeeDAO()
private val departmentDAO = TimesheetApp.applicationDatabase.departmentDAO()
private val addressDAO = TimesheetApp.applicationDatabase.addressDAO()

fun DepartmentEntity?.toDepartment(): Department? {
    return this?.run {
        Department(name, id)
    }
}

fun Department?.toEntity(): DepartmentEntity? {
    return this?.let {
        DepartmentEntity(it.name).apply {
            id = it.id
        }
    }
}

fun EmployeeEntity?.toEmployee(): Employee? {
    return runBlocking {
        this@toEmployee?.run {
            Employee(
                firstName = firstName,
                lastName = lastName,
                middleName = middleName,
                birthDate = birthDate,
                department = departmentDAO.getDepartmentByName(departmentName).toDepartment() ?: return@run null,
                position = position,
                address = addressDAO.getAddressByEmployeeId(id).toAddress(),
                phone = phone,
                id = id
            )
        }
    }
}

fun Employee?.toEntity(): EmployeeEntity? {
    return this?.run {
        EmployeeEntity(
            firstName = firstName,
            lastName = lastName,
            middleName = middleName,
            birthDate = birthDate,
            departmentName = department.name,
            position = position,
            phone = phone
        )
    }
}

fun AddressEntity?.toAddress(): Address? {
    return this?.run {
        Address(
            country = country,
            city = city,
            street = street,
            houseNumber = house,
            flatNumber = flat
        )
    }
}

fun Address?.toEntity(): AddressEntity? {
    return this?.run {
        AddressEntity(
            country = country,
            city = city,
            street = street,
            house = houseNumber,
            flat = flatNumber
        )
    }
}

fun MarkedEmployeeEntity?.toMarkedEmployee(): MarkedEmployee? {
    return runBlocking {
        this@toMarkedEmployee?.run {
            MarkedEmployee(
                date,
                employeeDAO.getById(employeeId).toEmployee() ?: return@runBlocking null,
                status
            )
        }
    }
}

fun MarkedEmployee?.toEntity(): MarkedEmployeeEntity? {
    return this?.let {
        MarkedEmployeeEntity(
            it.date,
            it.employee.id,
            it.status
        )
    }
}
