package ru.newuserkk.naukatesting.data.db

import androidx.room.TypeConverter
import java.util.*

class RoomConverters {
    @TypeConverter
    fun dateFromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}