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

package dev.n3shemmy3.coffre.ui.utils;

import androidx.annotation.NonNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

public class NumberUtils {
    public static BigDecimal parseNumberLocale(@NonNull String text) {
        try {
            NumberFormat format = NumberFormat.getNumberInstance(Locale.getDefault());
            return new BigDecimal(String.valueOf(format.parse(text)));
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }
    public static BigDecimal getAbsDecimalPart(BigDecimal bigDecimal) {
        BigDecimal absoluteBigDecimal = bigDecimal.abs();
        BigDecimal intPart = new BigDecimal(absoluteBigDecimal.toBigInteger());
        BigDecimal decimalPart = absoluteBigDecimal.subtract(intPart);
        return decimalPart.setScale(decimalPart.scale(), RoundingMode.DOWN);
    }
    public static BigDecimal formatAmount(BigDecimal balance) {
        return String.valueOf(balance).equals("null") ? BigDecimal.valueOf(0.00) : balance.setScale(2, RoundingMode.DOWN);
    }
    public static String formatCurrency(String currencySymbol, BigDecimal amount) {
        return String.format("%s %s", currencySymbol, formatAmount(amount));
    }

}
