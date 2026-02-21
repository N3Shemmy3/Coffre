package dev.n3shemmy3.coffre.data.source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import dev.n3shemmy3.coffre.domain.model.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {
    
    @Query("SELECT * FROM Transactions")
    fun observe(): Flow<List<Transaction>>
    
    @Query("SELECT * FROM Transactions WHERE :id")
    fun observe(id: Long): Flow<Transaction>


    @Query("SELECT * FROM Transactions")
    suspend fun get(): List<Transaction>

    @Query("SELECT * FROM Transactions WHERE :id")
    suspend fun get(id: Long): Transaction?


    @Upsert
    suspend fun upsert(item: Transaction)

    @Upsert
    suspend fun upsert(items: List<Transaction>)



    @Query("DELETE FROM Transactions WHERE :id")
    suspend fun delete(id: Long): Long

    @Query("DELETE FROM Transactions WHERE id in (:ids)")
    suspend fun delete(ids: List<Long>): Long
}