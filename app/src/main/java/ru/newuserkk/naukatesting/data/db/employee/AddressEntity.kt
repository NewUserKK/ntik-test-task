package ru.newuserkk.naukatesting.data.db.employee

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "address")
class AddressEntity(
    @ColumnInfo val country: String,
    @ColumnInfo val city: String,
    @ColumnInfo val street: String,
    @ColumnInfo val house: String,
    @ColumnInfo val flat: String?
) {
    @PrimaryKey(autoGenerate = true) var addressId: Long = 0
    @ColumnInfo var employeeId: Long = -1
}