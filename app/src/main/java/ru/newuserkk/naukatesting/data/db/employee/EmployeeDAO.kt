package ru.newuserkk.naukatesting.data.db.employee

import androidx.room.*
import ru.newuserkk.naukatesting.domain.employee.model.Employee

@Dao
interface EmployeeDAO {

    @Query("SELECT * FROM employees")
    suspend fun getAll(): List<Employee>

    @Query("SELECT * FROM employees WHERE id=:id")
    suspend fun getById(id: Long): Employee?

    @Insert
    suspend fun add(employee: Employee): Long

    @Update
    suspend fun update(employee: Employee)
}