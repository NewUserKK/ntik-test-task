package ru.newuserkk.timesheet.domain.timekeeper.model

import androidx.room.*
import ru.newuserkk.timesheet.domain.employee.model.Employee
import java.io.Serializable
import java.util.*

@Entity(
    tableName = "attendance",
    foreignKeys = [
        ForeignKey(
            entity = Employee::class,
            childColumns = ["employee_id"],
            parentColumns = ["id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class MarkedEmployee(
    @ColumnInfo val date: Date,
    @ColumnInfo(name = "employee_id") val employeeId: Long,
    @ColumnInfo val status: AttendanceStatus
) : Serializable {

    @Ignore lateinit var employee: Employee

    @PrimaryKey(autoGenerate = true) var id: Long = 0
}