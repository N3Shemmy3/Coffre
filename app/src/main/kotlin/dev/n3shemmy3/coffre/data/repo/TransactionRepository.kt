/*
 *
 *  * Copyright (C) 2025 Shemmy
 *  *
 *  * This program is free software: you can redistribute it and/or modify
 *  * it under the terms of the GNU General Public License as published by
 *  * the Free Software Foundation, version 3 of the License.
 *  *
 *  * This program is distributed in the hope that it will be useful,
 *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  * GNU General Public License for more details.
 *  *
 *  * You should have received a copy of the GNU General Public License
 *  * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 */

package dev.n3shemmy3.coffre.data.repo

import dev.n3shemmy3.coffre.data.dao.TransactionDao
import dev.n3shemmy3.coffre.data.entity.Transaction
import kotlinx.coroutines.flow.Flow
import java.math.BigDecimal

class TransactionRepository(private val dao: TransactionDao) {

    suspend fun insert(transaction: Transaction) = dao.insert(transaction)
    suspend fun insert(transactions: List<Transaction>) = dao.insert(transactions)

    suspend fun update(transaction: Transaction) = dao.update(transaction)
    suspend fun update(transactions: List<Transaction>) = dao.update(transactions)

    suspend fun deleteById(id: Long) = dao.deleteById(id)
    suspend fun delete(transactions: List<Transaction>) = dao.delete(transactions)

    suspend fun getById(id: Long): Transaction? = dao.getById(id)
    suspend fun getByIds(ids: List<Long>): Flow<List<Transaction>> = dao.getByIds(ids)
    suspend fun getAll(): Flow<List<Transaction>> = dao.getAll()

    fun getTotalIncomes(): Flow<BigDecimal> = dao.getTotalIncomes()
    fun getTotalExpenses(): Flow<BigDecimal> = dao.getTotalExpenses()
    fun getTotalBalance(): Flow<BigDecimal> = dao.getTotalBalance()
}