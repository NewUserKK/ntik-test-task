package ru.newuserkk.naukatesting.domain.employee.model

import androidx.room.*
import ru.newuserkk.naukatesting.domain.department.model.Department
import java.io.Serializable
import java.util.*

@Entity(
    tableName = "employees",
    foreignKeys = [
        ForeignKey(
            entity = Department::class,
            parentColumns = ["id"],
            childColumns = ["department_id"]
        )
    ]
)
data class Employee(
    @ColumnInfo val firstName: String,
    @ColumnInfo val lastName: String,
    @ColumnInfo val middleName: String?,
    @ColumnInfo val birthDate: Date,
    @ColumnInfo(name = "department_id") val departmentId: Long,
    @ColumnInfo val position: String,
    @Embedded val address: Address,
    @ColumnInfo val phone: String,
    @PrimaryKey(autoGenerate = true) var id: Long = 0
) : Serializable {

    override fun toString(): String {
        return getFullName()
    }

}

fun Employee.getFullName(): String {
    return "$lastName $firstName" + if (middleName != null) " $middleName" else ""
}