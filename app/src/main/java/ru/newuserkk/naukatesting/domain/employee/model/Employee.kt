package ru.newuserkk.naukatesting.domain.employee.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.newuserkk.naukatesting.domain.department.model.Department
import java.io.Serializable
import java.util.*

@Entity(tableName = "employees")
data class Employee(
    @ColumnInfo val firstName: String,
    @ColumnInfo val lastName: String,
    @ColumnInfo val middleName: String?,
    @ColumnInfo val birthDate: Date,
    @ColumnInfo val department: Department,
    @ColumnInfo val position: String,
    @ColumnInfo val address: Address?,
    @ColumnInfo val phone: String,
    @PrimaryKey(autoGenerate = true) var id: Long = 0
): Serializable {

    override fun toString(): String {
        return getFullName()
    }

}

fun Employee.getFullName(): String {
    return "$lastName $firstName" + if (middleName != null) " $middleName" else ""
}