package ru.newuserkk.naukatesting.data.db

import androidx.room.TypeConverter
import kotlinx.coroutines.runBlocking
import ru.newuserkk.naukatesting.TimesheetApp
import ru.newuserkk.naukatesting.domain.department.model.Department
import ru.newuserkk.naukatesting.domain.employee.model.Address
import ru.newuserkk.naukatesting.domain.employee.model.Employee
import ru.newuserkk.naukatesting.domain.timekeeper.model.AttendanceStatus
import java.util.*

class RoomConverters {
    @TypeConverter
    fun dateFromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun statusToId(status: AttendanceStatus?): Int? {
        return status?.id
    }

    @TypeConverter
    fun idToStatus(id: Int?): AttendanceStatus? {
        return AttendanceStatus.values().find { it.id == id }
    }

    @TypeConverter
    fun departmentToId(department: Department?): Long? {
        return department?.id
    }

    @TypeConverter
    fun departmentFromId(id: Long?): Department? {
        return runBlocking {
            id?.let {
                TimesheetApp.applicationDatabase.departmentDAO().getById(it)
            }
        }
    }

    @TypeConverter
    fun employeeFromId(id: Long?): Employee? {
        return runBlocking {
            System.err.println("converting employee from id")
            id?.let {
                TimesheetApp.applicationDatabase.employeeDAO().getById(it)
            }.also { System.err.println("employee from id ok: $it") }
        }
    }

    @TypeConverter
    fun employeeToId(employee: Employee?): Long? {
        System.err.println("converting employee to id")
        return employee?.id.also { System.err.println("employee to id ok") }
    }

    @TypeConverter
    fun addressFromId(id: Long?): Address? {
        return runBlocking {
            System.err.println("converting address from id")
            id?.let {
                TimesheetApp.applicationDatabase.addressDAO().getById(it)
            }.also { System.err.println("address from id ok: $it") }
        }
    }

    @TypeConverter
    fun addressToId(address: Address?): Long? {
        System.err.println("converting address to id")
        return address?.id.also { System.err.println("address to id ok") }
    }
}