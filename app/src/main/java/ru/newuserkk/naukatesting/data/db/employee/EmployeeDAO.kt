package ru.newuserkk.naukatesting.data.db.employee

import androidx.room.*
import ru.newuserkk.naukatesting.data.db.department.DepartmentEntity
import ru.newuserkk.naukatesting.domain.employee.model.Employee

@Dao
interface EmployeeDAO {

    @Query("SELECT * FROM employees")
    suspend fun getAll(): List<EmployeeEntity>

    @Query("SELECT * FROM employees WHERE id=:id")
    suspend fun getById(id: Long): EmployeeEntity

    @Insert
    suspend fun add(employee: EmployeeEntity): Long

    @Update
    suspend fun update(department: DepartmentEntity?)
}