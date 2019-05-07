package ru.newuserkk.naukatesting.data.db

import androidx.room.TypeConverter
import ru.newuserkk.naukatesting.domain.timekeeper.model.AttendanceStatus
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

    @TypeConverter
    fun statusToId(status: AttendanceStatus?): Int? {
        return status?.id
    }

    @TypeConverter
    fun idToStatus(id: Int?): AttendanceStatus? {
        return AttendanceStatus.values().find { it.id == id }
    }
}