package ru.newuserkk.naukatesting.data.db.employee

import androidx.room.*

@Dao
interface AddressDAO {

    @Query("SELECT * FROM address WHERE employeeId=:id")
    suspend fun getAddressByEmployeeId(id: Long): AddressEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun add(address: AddressEntity?): Long

    @Query("UPDATE address SET employeeId=:employeeId WHERE addressId=:addressId")
    suspend fun updateEmployeeId(addressId: Long, employeeId: Long)
}