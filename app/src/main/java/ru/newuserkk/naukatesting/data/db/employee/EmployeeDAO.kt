package ru.newuserkk.naukatesting.data.db.employee

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.newuserkk.naukatesting.domain.employee.model.Employee

@Dao
interface EmployeeDAO {

    @Query("SELECT * FROM employees")
    suspend fun getAll(): List<EmployeeEntity>

    @Query("SELECT * FROM employees WHERE id=:id")
    suspend fun getById(id: Long): EmployeeEntity

    @Insert
    suspend fun addEmployee(employee: EmployeeEntity): Long
}