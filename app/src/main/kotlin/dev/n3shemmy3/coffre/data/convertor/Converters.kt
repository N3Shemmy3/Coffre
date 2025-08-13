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

package dev.n3shemmy3.coffre.data.convertor

import android.icu.math.BigDecimal
import androidx.room.TypeConverter
import dev.n3shemmy3.coffre.data.entity.Transaction
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Converters {
    private val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

    @TypeConverter
    fun fromTimestamp(value: String?): LocalDateTime? =
        value?.let { LocalDateTime.parse(it, formatter) }

    @TypeConverter
    fun localDateTimeToTimestamp(date: LocalDateTime?): String? =
        date?.format(formatter)

    @TypeConverter
    fun fromTransactionType(value: String?): Transaction.Type? =
        value?.let { Transaction.Type.valueOf(it) }

    @TypeConverter
    fun transactionTypeToString(type: Transaction.Type?): String? =
        type?.name

    @TypeConverter
    fun fromBigDecimal(value: BigDecimal?): String? =
        value?.toString()

    @TypeConverter
    fun toBigDecimal(value: String?): BigDecimal? =
        value?.let { BigDecimal(it) }
}
