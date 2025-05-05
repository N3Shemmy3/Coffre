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
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

import dev.n3shemmy3.coffre.backend.entity.Currency;

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

    public static BigDecimal formatAmount(Currency currency, BigDecimal balance) {
        return String.valueOf(balance).equals("null") ? BigDecimal.valueOf(0) : balance.setScale(0, Integer.parseInt(currency.getDecimal_digits()) > 0 ? RoundingMode.DOWN : RoundingMode.HALF_UP);
    }

    public static BigDecimal formatAmount(BigDecimal balance) {
        return String.valueOf(balance).equals("null") ? BigDecimal.valueOf(0) : balance.setScale(0, RoundingMode.DOWN);
    }

    public static String formatCurrency(Currency currency, BigDecimal amount) {
        StringBuilder pattern = new StringBuilder("#,##0");
        int fractionDigits = Integer.parseInt(currency.getDecimal_digits());
        String currencySymbol = currency.getSymbol().isEmpty() ? currency.getCode() : currency.getSymbol();
        if (fractionDigits > 0) {
            pattern.append(".");
            for (int i = 0; i < fractionDigits; i++) {
                pattern.append("0");
            }
        }
        DecimalFormat formatter = new DecimalFormat(pattern.toString());
        return String.format("%s %s", currencySymbol, Objects.equals(amount, BigDecimal.ZERO) ? formatter.format(BigDecimal.ZERO) :
                formatter.format(amount.setScale(0, Integer.parseInt(currency.getDecimal_digits()) > 0 ? RoundingMode.DOWN : RoundingMode.HALF_UP)));
    }

}
