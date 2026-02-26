package dev.n3shemmy3.coffre.data.source

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import dev.n3shemmy3.coffre.domain.model.Account
import kotlinx.coroutines.flow.Flow
import java.math.BigDecimal

@Dao
interface AccountDao {

    @Query("SELECT * FROM Accounts")
    fun observe(): Flow<List<Account>>

    @Query("SELECT * FROM Accounts WHERE :id")
    fun observe(id: Long): Flow<Account>


    @Query("SELECT * FROM Accounts")
    suspend fun get(): List<Account>

    @Query("SELECT * FROM Accounts WHERE :id")
    suspend fun get(id: Long): Account?

    @Upsert
    suspend fun upsert(item: Account)

    @Upsert
    suspend fun upsert(items: List<Account>)

    @Query("SELECT COALESCE(SUM(balance), 0) FROM accounts WHERE isPublic = true")
    fun totalBalance(): Flow<BigDecimal>


    @Query("DELETE FROM Accounts WHERE :id")
    suspend fun delete(id: Long): Int

    @Query("DELETE FROM Accounts WHERE id in (:ids)")
    suspend fun delete(ids: List<Long>): Int
}