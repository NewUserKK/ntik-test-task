package ru.newuserkk.naukatesting.domain.employee.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "address")
data class Address(
    @ColumnInfo val country: String,
    @ColumnInfo val city: String,
    @ColumnInfo val street: String,
    @ColumnInfo val house: String,
    @ColumnInfo val flat: String?,
    @ColumnInfo var employee: Employee? = null,
    @PrimaryKey(autoGenerate = true) var id: Long = 0
): Serializable