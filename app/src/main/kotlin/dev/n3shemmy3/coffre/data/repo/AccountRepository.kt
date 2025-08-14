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

import dev.n3shemmy3.coffre.data.dao.AccountDao
import dev.n3shemmy3.coffre.data.entity.Account
import kotlinx.coroutines.flow.Flow

class AccountRepository(private val dao: AccountDao) {
    suspend fun insert(account: Account) = dao.insert(account)
    suspend fun insert(accounts: List<Account>) = dao.insert(accounts)

    suspend fun update(account: Account) = dao.update(account)
    suspend fun update(accounts: List<Account>) = dao.update(accounts)

    suspend fun deleteById(id: Long) = dao.deleteById(id)
    suspend fun delete(accounts: List<Account>) = dao.delete(accounts)

    suspend fun getById(id: Long): Account? = dao.getById(id)
    suspend fun getByIds(ids: List<Long>): Flow<List<Account>> = dao.getByIds(ids)
    suspend fun getAll(): Flow<List<Account>> = dao.getAll()
}