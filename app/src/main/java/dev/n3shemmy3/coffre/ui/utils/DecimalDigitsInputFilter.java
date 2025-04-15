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

import android.text.InputFilter;
import android.text.Spanned;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DecimalDigitsInputFilter implements InputFilter {
    private static String separators = "[\\.\\,]";

    private final Pattern mPattern;

    public DecimalDigitsInputFilter(int digitsBeforeSeparator, int digitsAfterSeparator) {
        String b = "(-?\\d{1," + digitsBeforeSeparator + "})";
        String a = "(\\d{1," + digitsAfterSeparator + "})";
        String s = separators;

        String numberRegex = new StringBuilder()
                .append("(-)")
                .append("|")
                .append("(" + b + s + a + ")")
                .append("|")
                .append("(" + b + s +")")
                .append("|")
                .append("(" + b + ")")
                .toString();

        mPattern = Pattern.compile(numberRegex);
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        String input = dest.toString().substring(0, dstart) + source.
                subSequence(start, end) + dest.toString().substring(dend);

        Matcher matcher = mPattern.matcher(input);

        if (!matcher.matches()) {
            return "";
        }

        return null;
    }
}