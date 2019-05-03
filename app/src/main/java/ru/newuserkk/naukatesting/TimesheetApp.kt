package ru.newuserkk.naukatesting

import android.app.Application
import androidx.room.Room
import ru.newuserkk.naukatesting.data.db.ApplicationDatabase

class TimesheetApp: Application() {

    companion object {
        lateinit var applicationDatabase: ApplicationDatabase
    }

    override fun onCreate() {
        super.onCreate()
        applicationDatabase = Room
            .databaseBuilder(applicationContext, ApplicationDatabase::class.java, "ApplicationDatabase")
            .build()
    }
}