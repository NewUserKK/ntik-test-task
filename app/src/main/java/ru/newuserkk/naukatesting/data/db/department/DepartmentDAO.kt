package ru.newuserkk.naukatesting.data.db.department

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ru.newuserkk.naukatesting.domain.department.model.Department

@Dao
interface DepartmentDAO {
    @Query("SELECT * FROM departments")
    suspend fun getAll(): List<Department>

    @Query("SELECT * FROM departments WHERE name=:name")
    suspend fun getDepartmentByName(name: String): Department?

    @Query("SELECT * FROM departments WHERE id=:id")
    suspend fun getDepartmentById(id: Long): Department?

    @Insert
    suspend fun add(department: Department?): Long

    @Update
    suspend fun update(department: Department?): Int
}