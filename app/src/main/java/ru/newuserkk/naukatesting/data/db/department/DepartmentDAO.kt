package ru.newuserkk.naukatesting.data.db.department

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.newuserkk.naukatesting.domain.department.model.Department

@Dao
interface DepartmentDAO {
    @Query("SELECT * FROM departments")
    suspend fun getAll(): List<DepartmentEntity>

    @Query("SELECT * FROM departments WHERE name=:name")
    suspend fun getDepartmentByName(name: String): Department

    @Insert
    suspend fun add(department: DepartmentEntity?)
}