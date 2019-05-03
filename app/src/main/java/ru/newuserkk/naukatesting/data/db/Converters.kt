package ru.newuserkk.naukatesting.data.db

import androidx.room.TypeConverter
import kotlinx.coroutines.runBlocking
import ru.newuserkk.naukatesting.TimesheetApp
import ru.newuserkk.naukatesting.data.db.department.DepartmentEntity
import ru.newuserkk.naukatesting.data.db.employee.AddressEntity
import ru.newuserkk.naukatesting.data.db.employee.EmployeeEntity
import ru.newuserkk.naukatesting.domain.department.model.Department
import ru.newuserkk.naukatesting.domain.employee.model.Address
import ru.newuserkk.naukatesting.domain.employee.model.Employee
import java.util.*

object Converters {

    private val departmentDAO = TimesheetApp.applicationDatabase.departmentDAO()
    private val addressDAO = TimesheetApp.applicationDatabase.addressDAO()

    fun departmentToEntity(department: Department?): DepartmentEntity? {
        return department?.run { DepartmentEntity(name) }
    }

    fun departmentFromEntity(entity: DepartmentEntity?): Department? {
        return entity?.run {
            Department(name)
        }
    }

    fun employeeFromEntity(entity: EmployeeEntity?): Employee? {
        return runBlocking {
            entity?.run {
                System.err.println("id: $id")
                Employee(
                    firstName = firstName,
                    lastName = lastName,
                    middleName = middleName,
                    birthDate = birthDate,
                    department = departmentDAO.getDepartmentByName(departmentName),
                    position = position,
                    address = Converters.addressFromEntity(addressDAO.getAddressByEmployeeId(id)),
                    phone = phone,
                    id = id
                )
            }
        }
    }

    fun employeeToEntity(employee: Employee?): EmployeeEntity? {
        return employee?.run {
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

    fun addressFromEntity(entity: AddressEntity?): Address? {
        return entity?.run {
            Address(
                country = country,
                city = city,
                street = street,
                houseNumber = house,
                flatNumber = flat
            )
        }
    }

    fun addressToEntity(address: Address?): AddressEntity? {
        return address?.run {
            AddressEntity(
                country = country,
                city = city,
                street = street,
                house = houseNumber,
                flat = flatNumber
            )
        }
    }
}