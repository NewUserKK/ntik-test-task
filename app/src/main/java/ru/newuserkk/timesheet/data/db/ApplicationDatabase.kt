package ru.newuserkk.timesheet.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.newuserkk.timesheet.data.db.department.DepartmentDAO
import ru.newuserkk.timesheet.data.db.employee.EmployeeDAO
import ru.newuserkk.timesheet.data.db.timekeeper.CalendarDAO
import ru.newuserkk.timesheet.domain.department.model.Department
import ru.newuserkk.timesheet.domain.employee.model.Employee
import ru.newuserkk.timesheet.domain.timekeeper.model.MarkedEmployee


@Database(
    entities = [
        Department::class,
        Employee::class,
        MarkedEmployee::class
    ],
    version = 1
)
@TypeConverters(RoomConverters::class)
abstract class ApplicationDatabase : RoomDatabase() {
    abstract fun departmentDAO(): DepartmentDAO
    abstract fun employeeDAO(): EmployeeDAO
//    abstract fun addressDAO(): AddressDAO
    abstract fun calendarDAO(): CalendarDAO
}