package ru.newuserkk.timesheet.data.db

import androidx.room.TypeConverter
import ru.newuserkk.timesheet.domain.timekeeper.model.AttendanceStatus
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

//    @TypeConverter
//    fun departmentFromId(id: Long?): Department? {
//        return runBlocking {
//            id?.let {
//                TimesheetApp.applicationDatabase.departmentDAO().getById(it)
//            }
//        }
//    }
//
//    @TypeConverter
//    fun departmentToId(departmentId: Department?): Long? {
//        return departmentId?.id
//    }
//
//    @TypeConverter
//    fun employeeFromId(id: Long?): Employee? {
//        return runBlocking {
//            System.err.println("converting employee from id")
//            id?.let {
//                TimesheetApp.applicationDatabase.employeeDAO().getById(it)
//            }.also { System.err.println("employee from id ok: $it") }
//        }
//    }
//
//    @TypeConverter
//    fun employeeToId(employee: Employee?): Long? {
//        System.err.println("converting employee to id")
//        return employee?.id.also { System.err.println("employee to id ok") }
//    }
//
//    @TypeConverter
//    fun addressFromId(id: Long?): Address? {
//        return runBlocking {
//            System.err.println("converting address from id")
//            id?.let {
//                TimesheetApp.applicationDatabase.addressDAO().getById(it)
//            }.also { System.err.println("address from id ok: $it") }
//        }
//    }
//
//    @TypeConverter
//    fun addressToId(address: Address?): Long? {
//        System.err.println("converting address to id")
//        return address?.id.also { System.err.println("address to id ok") }
//    }
}