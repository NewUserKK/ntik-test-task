package ru.newuserkk.naukatesting.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import ru.newuserkk.naukatesting.data.db.department.DepartmentDAO
import ru.newuserkk.naukatesting.data.db.department.DepartmentEntity
import ru.newuserkk.naukatesting.data.db.employee.AddressDAO
import ru.newuserkk.naukatesting.data.db.employee.AddressEntity
import ru.newuserkk.naukatesting.data.db.employee.EmployeeDAO
import ru.newuserkk.naukatesting.data.db.employee.EmployeeEntity


@Database(
    entities = [
        DepartmentEntity::class,
        EmployeeEntity::class,
        AddressEntity::class
    ],
    version = 1
)
@TypeConverters(RoomConverters::class)
abstract class ApplicationDatabase : RoomDatabase() {
    abstract fun departmentDAO(): DepartmentDAO
    abstract fun employeeDAO(): EmployeeDAO
    abstract fun addressDAO(): AddressDAO
}