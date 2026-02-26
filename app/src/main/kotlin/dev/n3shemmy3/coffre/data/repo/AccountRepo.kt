package dev.n3shemmy3.coffre.data.repo

import dev.n3shemmy3.coffre.data.source.AccountDao
import dev.n3shemmy3.coffre.domain.model.Account
import kotlinx.coroutines.flow.Flow
import java.math.BigDecimal

class AccountRepo(
    private val localDataSource: AccountDao
) : Repository<Account> {


    override fun observe(id: Long): Flow<Account> {
        return localDataSource.observe(id)
    }

    override fun observe(): Flow<List<Account>> {
        return localDataSource.observe()
    }

    override suspend fun get(id: Long): Account? {
        return localDataSource.get(id)
    }

    override suspend fun get(): List<Account> {
        return localDataSource.get()
    }

    suspend fun totalBalance(): Flow<BigDecimal> {
        return localDataSource.totalBalance()
    }

    override suspend fun upsert(item: Account) {
        localDataSource.upsert(item)
    }

    override suspend fun upsert(items: List<Account>) {
        localDataSource.upsert(items)
    }

    override suspend fun delete(id: Long): Int {
        return localDataSource.delete(id)
    }

    override suspend fun delete(ids: List<Long>): Int {
        return localDataSource.delete(ids)
    }
}