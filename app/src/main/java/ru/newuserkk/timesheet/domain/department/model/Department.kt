package ru.newuserkk.timesheet.domain.department.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "departments")
data class Department(
    @ColumnInfo val name: String,
    @PrimaryKey(autoGenerate = true) var id: Long? = null
) : Serializable {

    @ColumnInfo var deleted: Boolean = false

    override fun toString(): String {
        return name
    }

}