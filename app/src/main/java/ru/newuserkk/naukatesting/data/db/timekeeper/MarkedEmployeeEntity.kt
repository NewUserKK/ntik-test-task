package ru.newuserkk.naukatesting.data.db.timekeeper

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.newuserkk.naukatesting.domain.timekeeper.model.AttendanceStatus
import java.util.*

@Entity(tableName = "attendance")
class MarkedEmployeeEntity(
    @ColumnInfo val date: Date,
    @ColumnInfo val employeeId: Long,
    @ColumnInfo val status: AttendanceStatus
) {

    @PrimaryKey(autoGenerate = true)
    var id = 0L

}