package ru.newuserkk.naukatesting.domain.employee.model

import androidx.room.ColumnInfo
import java.io.Serializable

data class Address(
    @ColumnInfo val country: String,
    @ColumnInfo val city: String,
    @ColumnInfo val street: String,
    @ColumnInfo val house: String,
    @ColumnInfo val flat: String?
): Serializable