package dev.n3shemmy3.coffre.backend.item;
/*
 * Copyright (C) 2025 Shemmy
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

import androidx.annotation.NonNull;

import java.util.Objects;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class Currency {
    public static final String key ="currency";
    @Id
    private long id;
    private String name;
    private String name_plural;
    private String code;
    private String decimal_digits;
    private String symbol;
    private String symbol_native;
    private double rounding;

    public Currency(long id, String name, String name_plural, String code, String decimal_digits, String symbol, String symbol_native, double rounding) {
        this.id = id;
        this.name = name;
        this.name_plural = name_plural;
        this.code = code;
        this.decimal_digits = decimal_digits;
        this.symbol = symbol;
        this.symbol_native = symbol_native;
        this.rounding = rounding;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName_plural() {
        return name_plural;
    }

    public void setName_plural(String name_plural) {
        this.name_plural = name_plural;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDecimal_digits() {
        return decimal_digits;
    }

    public void setDecimal_digits(String decimal_digits) {
        this.decimal_digits = decimal_digits;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol_native() {
        return symbol_native;
    }

    public void setSymbol_native(String symbol_native) {
        this.symbol_native = symbol_native;
    }

    public double getRounding() {
        return rounding;
    }

    public void setRounding(double rounding) {
        this.rounding = rounding;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Currency)) return false;

        Currency currency = (Currency) o;
        return getId() == currency.getId() && Double.compare(getRounding(), currency.getRounding()) == 0 && Objects.equals(getName(), currency.getName()) && Objects.equals(getName_plural(), currency.getName_plural()) && Objects.equals(getCode(), currency.getCode()) && Objects.equals(getDecimal_digits(), currency.getDecimal_digits()) && Objects.equals(getSymbol(), currency.getSymbol()) && Objects.equals(getSymbol_native(), currency.getSymbol_native());
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(getId());
        result = 31 * result + Objects.hashCode(getName());
        result = 31 * result + Objects.hashCode(getName_plural());
        result = 31 * result + Objects.hashCode(getCode());
        result = 31 * result + Objects.hashCode(getDecimal_digits());
        result = 31 * result + Objects.hashCode(getSymbol());
        result = 31 * result + Objects.hashCode(getSymbol_native());
        result = 31 * result + Double.hashCode(getRounding());
        return result;
    }

    @NonNull
    @Override
    public String toString() {
        return "Currency{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", name_plural='" + name_plural + '\'' +
                ", code='" + code + '\'' +
                ", decimal_digits='" + decimal_digits + '\'' +
                ", symbol='" + symbol + '\'' +
                ", symbol_native='" + symbol_native + '\'' +
                ", rounding=" + rounding +
                '}';
    }
}
