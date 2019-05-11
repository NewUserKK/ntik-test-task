package ru.newuserkk.naukatesting.data.db.timekeeper

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.newuserkk.naukatesting.domain.timekeeper.model.MarkedEmployee
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
    suspend fun addMark(employee: MarkedEmployee?): Long
}