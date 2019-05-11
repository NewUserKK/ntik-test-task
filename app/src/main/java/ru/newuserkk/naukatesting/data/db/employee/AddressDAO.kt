package ru.newuserkk.naukatesting.data.db.employee

import androidx.room.*
import ru.newuserkk.naukatesting.domain.employee.model.Address
import ru.newuserkk.naukatesting.domain.employee.model.Employee

@Dao
interface AddressDAO {

    @Query("SELECT * FROM address WHERE id=:id")
    suspend fun getById(id: Long): Address?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(address: Address?): Long?
}