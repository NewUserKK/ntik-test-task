package ru.newuserkk.timesheet

import android.app.Application
import androidx.room.Room
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton
import ru.newuserkk.timesheet.data.db.ApplicationDatabase
import ru.newuserkk.timesheet.data.repository.department.DepartmentRoomRepository
import ru.newuserkk.timesheet.data.repository.employee.EmployeeRoomRepository
import ru.newuserkk.timesheet.data.repository.timekeeper.CalendarRoomRepository
import ru.newuserkk.timesheet.domain.department.DepartmentInteractor
import ru.newuserkk.timesheet.domain.department.DepartmentInteractorImpl
import ru.newuserkk.timesheet.domain.department.DepartmentRepository
import ru.newuserkk.timesheet.domain.employee.EmployeeInteractor
import ru.newuserkk.timesheet.domain.employee.EmployeeInteractorImpl
import ru.newuserkk.timesheet.domain.employee.EmployeeRepository
import ru.newuserkk.timesheet.domain.timekeeper.CalendarInteractor
import ru.newuserkk.timesheet.domain.timekeeper.CalendarInteractorImpl
import ru.newuserkk.timesheet.domain.timekeeper.CalendarRepository

class TimesheetApp: Application() {

    companion object {
        lateinit var applicationDatabase: ApplicationDatabase
        lateinit var kodein: Kodein
    }

    override fun onCreate() {
        super.onCreate()
        applicationDatabase = Room
            .databaseBuilder(applicationContext, ApplicationDatabase::class.java, "ApplicationDatabase")
            .build()
        kodein = Kodein {
            bind<DepartmentInteractor>() with singleton { DepartmentInteractorImpl() }
            bind<DepartmentRepository>() with singleton { DepartmentRoomRepository() }

            bind<EmployeeInteractor>() with singleton { EmployeeInteractorImpl() }
            bind<EmployeeRepository>() with singleton { EmployeeRoomRepository() }

            bind<CalendarInteractor>() with singleton { CalendarInteractorImpl() }
            bind<CalendarRepository>() with singleton { CalendarRoomRepository() }
        }
    }
}