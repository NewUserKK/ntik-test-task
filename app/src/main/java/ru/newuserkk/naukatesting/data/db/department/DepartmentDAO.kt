package ru.newuserkk.naukatesting.data.db.department

import androidx.room.*
import ru.newuserkk.naukatesting.domain.department.model.Department

@Dao
interface DepartmentDAO {
    @Query("SELECT * FROM departments WHERE deleted=0")
    suspend fun getAll(): List<Department>

    @Query("SELECT * FROM departments WHERE name=:name")
    suspend fun getByName(name: String): Department?

    @Query("SELECT * FROM departments WHERE id=:id")
    suspend fun getById(id: Long): Department?

    @Insert
    suspend fun add(department: Department): Long

    @Update
    suspend fun update(department: Department)

    @Delete
    suspend fun remove(department: Department)
}