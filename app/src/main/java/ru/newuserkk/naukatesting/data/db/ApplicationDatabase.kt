package ru.newuserkk.naukatesting.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.newuserkk.naukatesting.data.db.department.DepartmentDAO
import ru.newuserkk.naukatesting.data.db.employee.EmployeeDAO
import ru.newuserkk.naukatesting.data.db.timekeeper.CalendarDAO
import ru.newuserkk.naukatesting.domain.department.model.Department
import ru.newuserkk.naukatesting.domain.employee.model.Address
import ru.newuserkk.naukatesting.domain.employee.model.Employee
import ru.newuserkk.naukatesting.domain.timekeeper.model.MarkedEmployee


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