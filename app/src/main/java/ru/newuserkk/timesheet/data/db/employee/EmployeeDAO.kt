package ru.newuserkk.timesheet.data.db.employee

import androidx.room.*
import ru.newuserkk.timesheet.domain.employee.model.Employee

@Dao
interface EmployeeDAO {

    @Query("SELECT * FROM employees WHERE deleted=0")
    suspend fun getAll(): List<Employee>

    @Query("SELECT * FROM employees WHERE id=:id")
    suspend fun getById(id: Long): Employee?

    @Insert
    suspend fun add(employee: Employee): Long

    @Update
    suspend fun update(employee: Employee)

    @Delete
    suspend fun remove(employee: Employee)
}