package dev.n3shemmy3.coffre.data.repo

import dev.n3shemmy3.coffre.data.source.TransactionDao
import dev.n3shemmy3.coffre.domain.model.Transaction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

interface TransactionRepo {

    fun observe(id: Long): Flow<Transaction>
    fun observe(): Flow<List<Transaction>>

    suspend fun get(id: Long): Transaction?
    suspend fun get(): List<Transaction>

    suspend fun upsert(item: Transaction)
    suspend fun upsert(items: List<Transaction>)

    suspend fun delete(id: Long): Long
    suspend fun delete(ids: List<Long>): Long
}

class TransactionRepoImpl(
    private val localDataSource: TransactionDao
) : TransactionRepo {

    override fun observe(id: Long): Flow<Transaction> {
        return localDataSource.observe(id)
    }

    override fun observe(): Flow<List<Transaction>> {
        return localDataSource.observe()
    }

    override suspend fun get(id: Long): Transaction? {
        return localDataSource.get(id)
    }

    override suspend fun get(): List<Transaction> {
        return localDataSource.get()
    }

    override suspend fun upsert(item: Transaction) {
        localDataSource.upsert(item)
    }

    override suspend fun upsert(items: List<Transaction>) {
        localDataSource.upsert(items)
    }

    override suspend fun delete(id: Long): Long {
        return localDataSource.delete(id)
    }

    override suspend fun delete(ids: List<Long>): Long {
        return localDataSource.delete(ids)
    }
}