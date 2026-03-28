package dev.n3shemmy3.coffre.data.repo

import dev.n3shemmy3.coffre.data.source.TransactionDao
import dev.n3shemmy3.coffre.domain.model.Transaction
import kotlinx.coroutines.flow.Flow
import java.math.BigDecimal

class TransactionRepo(
    private val localDataSource: TransactionDao
) : Repository<Transaction> {

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

    suspend fun totalIncome(): Flow<BigDecimal> {
        return localDataSource.totalIncome()
    }

    suspend fun totalExpense(): Flow<BigDecimal> {
        return localDataSource.totalExpense()
    }

    suspend fun totalTransfer(): Flow<BigDecimal> {
        return localDataSource.totalTransfer()
    }

    override suspend fun upsert(item: Transaction) {
        localDataSource.upsert(item)
    }

    override suspend fun upsert(items: List<Transaction>) {
        localDataSource.upsert(items)
    }

    override suspend fun delete(item: Transaction) {
        localDataSource.delete(item)
    }

    override suspend fun delete(ids: List<Long>): Int {
        return localDataSource.delete(ids)
    }
}