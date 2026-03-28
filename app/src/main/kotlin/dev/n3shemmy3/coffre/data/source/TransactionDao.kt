package dev.n3shemmy3.coffre.data.source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import dev.n3shemmy3.coffre.domain.model.Transaction
import kotlinx.coroutines.flow.Flow
import java.math.BigDecimal

@Dao
interface TransactionDao {

    @Query("SELECT * FROM Transactions")
    fun observe(): Flow<List<Transaction>>

    @Query("SELECT * FROM Transactions WHERE id = :id ")
    fun observe(id: Long): Flow<Transaction>


    @Query("SELECT * FROM Transactions")
    suspend fun get(): List<Transaction>

    @Query("SELECT * FROM Transactions WHERE id = :id ")
    suspend fun get(id: Long): Transaction?

    @Query("SELECT COALESCE(SUM(amount), 0) FROM transactions WHERE type = 0")
    fun totalIncome(): Flow<BigDecimal>

    @Query("SELECT COALESCE(SUM(amount), 0) FROM transactions WHERE type = 1")
    fun totalExpense(): Flow<BigDecimal>

    @Query("SELECT COALESCE(SUM(amount), 0) FROM transactions WHERE type = 2")
    fun totalTransfer(): Flow<BigDecimal>

    @Upsert
    suspend fun upsert(item: Transaction)

    @Upsert
    suspend fun upsert(items: List<Transaction>)


    @Delete
    suspend fun delete(item: Transaction)

    @Query("DELETE FROM Transactions WHERE id = :id")
    suspend fun delete(id: Long): Int

    @Query("DELETE FROM Transactions WHERE id in (:ids)")
    suspend fun delete(ids: List<Long>): Int
}