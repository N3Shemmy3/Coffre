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

public class Currency {
    private String name;
    private String code;
    private String symbol;

    public Currency(String name, String code, String symbol) {
        this.name = name;
        this.code = code;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @NonNull
    @Override
    public String toString() {
        return "Currency{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", symbol='" + symbol + '\'' +
                '}';
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Currency)) return false;

        Currency currency = (Currency) o;
        return getName().equals(currency.getName()) && Objects.equals(getCode(), currency.getCode()) && Objects.equals(getSymbol(), currency.getSymbol());
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + Objects.hashCode(getCode());
        result = 31 * result + Objects.hashCode(getSymbol());
        return result;
    }
}
