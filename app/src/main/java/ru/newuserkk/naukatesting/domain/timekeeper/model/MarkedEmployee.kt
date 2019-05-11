package ru.newuserkk.naukatesting.domain.timekeeper.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.newuserkk.naukatesting.domain.employee.model.Employee
import java.io.Serializable
import java.util.*

@Entity(tableName = "attendance")
data class MarkedEmployee(
    @ColumnInfo val date: Date,
    @ColumnInfo val employee: Employee,
    @ColumnInfo val status: AttendanceStatus
): Serializable {

    @PrimaryKey(autoGenerate = true)
    var id = 0L

}