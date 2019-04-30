package ru.newuserkk.naukatesting.data.db.department

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.newuserkk.naukatesting.domain.department.model.Department

@Database(entities = [DepartmentEntity::class], version = 1)
abstract class DepartmentDatabase: RoomDatabase() {

    companion object {
        const val LOG_TAG = "DepartmentDatabase"
    }

    abstract fun departmentDAO(): DepartmentDAO
}