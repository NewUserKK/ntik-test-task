package ru.newuserkk.naukatesting.data.db.employee

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "employees")
data class EmployeeEntity(
    @ColumnInfo val firstName: String,
    @ColumnInfo val lastName: String,
    @ColumnInfo val middleName: String?,
    @ColumnInfo val birthDate: Date,
    @ColumnInfo val departmentName: String,
    @ColumnInfo val position: String,
    @ColumnInfo val phone: String
) {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

}