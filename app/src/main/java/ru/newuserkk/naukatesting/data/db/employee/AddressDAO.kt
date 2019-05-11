package ru.newuserkk.naukatesting.data.db.employee

import androidx.room.*
import ru.newuserkk.naukatesting.domain.employee.model.Address
import ru.newuserkk.naukatesting.domain.employee.model.Employee

@Dao
interface AddressDAO {

    @Query("SELECT * FROM address WHERE id=:id")
    suspend fun getById(id: Long): Address?

    @Query("SELECT * FROM address WHERE employee=:employee")
    suspend fun getAddressByEmployee(employee: Employee?): Address?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun add(address: Address?): Long?

    @Query("UPDATE address SET employee=:employee WHERE id=:addressId")
    suspend fun updateEmployee(addressId: Long, employee: Employee)
}