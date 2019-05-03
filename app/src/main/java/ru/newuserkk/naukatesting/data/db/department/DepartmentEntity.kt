package ru.newuserkk.naukatesting.data.db.department

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "departments")
data class DepartmentEntity(@PrimaryKey var name: String)