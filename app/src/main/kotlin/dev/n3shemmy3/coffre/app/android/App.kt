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

package dev.n3shemmy3.coffre.app.android

import android.app.Application
import dev.n3shemmy3.coffre.data.entity.Account
import dev.n3shemmy3.coffre.data.entity.DEFAULT_ACCOUNT_NAME
import dev.n3shemmy3.coffre.data.repo.AccountRepository
import dev.n3shemmy3.coffre.data.repo.TransactionRepository
import dev.n3shemmy3.coffre.data.room.AppDatabase

class App : Application() {
    val database by lazy { AppDatabase.getInstance(this) }
    val accountRepository by lazy { AccountRepository(database.accountDao()) }
    val transactionRepository by lazy { TransactionRepository(database.transactionDao()) }
}