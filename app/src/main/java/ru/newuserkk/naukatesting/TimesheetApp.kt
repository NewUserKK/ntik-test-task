package ru.newuserkk.naukatesting

import android.app.Application
import androidx.room.Room
import ru.newuserkk.naukatesting.data.db.department.DepartmentDatabase

class TimesheetApp: Application() {

    companion object {
        lateinit var departmentDatabase: DepartmentDatabase
    }

    override fun onCreate() {
        super.onCreate()
        departmentDatabase = Room
            .databaseBuilder(applicationContext, DepartmentDatabase::class.java, "DepartmentDatabase")
            .build()
    }
}