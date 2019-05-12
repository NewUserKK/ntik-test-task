package ru.newuserkk.timesheet.data.db.timekeeper

import androidx.room.*
import ru.newuserkk.timesheet.domain.timekeeper.model.MarkedEmployee
import java.util.*

@Dao
interface CalendarDAO {

    @Query("SELECT * FROM attendance WHERE date=:date")
    suspend fun getEmployeesByDate(date: Date): List<MarkedEmployee>

    @Query("SELECT * FROM attendance")
    suspend fun getAll(): List<MarkedEmployee>

    @Query("SELECT * FROM attendance WHERE id=:id")
    suspend fun getById(id: Long): MarkedEmployee?

    @Insert
    suspend fun add(mark: MarkedEmployee): Long

    @Update
    suspend fun update(mark: MarkedEmployee)

    @Delete
    suspend fun remove(mark: MarkedEmployee)
}