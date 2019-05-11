package ru.newuserkk.naukatesting.domain.department.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "departments")
data class Department(
    @ColumnInfo val name: String,
    @PrimaryKey(autoGenerate = true) var id: Long = 0
) : Serializable {

    override fun toString(): String {
        return name
    }

}