package dev.n3shemmy3.coffre.backend.entity;
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

import java.math.BigDecimal;

import dev.n3shemmy3.coffre.backend.entity.Transaction.Type;
import io.objectbox.annotation.Convert;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.converter.PropertyConverter;

@Entity
public class Amount {
    @Id
    private long id;

    private long transactionId;

    @Convert(dbType = String.class, converter = BigDecimalConverter.class)
    private BigDecimal value;
    @Convert(dbType = Integer.class, converter = Transaction.TypeConverter.class)
    private Type type; // New field to store the transaction type

    public Amount() {
    }

    public Amount(long transactionId, BigDecimal value, Type type) { // Updated constructor
        this.transactionId = transactionId;
        this.value = value;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    // Converter for BigDecimal (no changes needed, but included for completeness)
    static class BigDecimalConverter implements PropertyConverter<BigDecimal, String> {
        @Override
        public BigDecimal convertToEntityProperty(String databaseValue) {
            return databaseValue == null ? null : new BigDecimal(databaseValue);
        }

        @Override
        public String convertToDatabaseValue(BigDecimal entityProperty) {
            return entityProperty == null ? null : entityProperty.toPlainString();
        }
    }

    // Converter for Type
    static class TypeConverter implements PropertyConverter<Type, String> {
        @Override
        public Type convertToEntityProperty(String databaseValue) {
            if (databaseValue == null) {
                return null; // Or a default value, e.g., Type.EXPENSE
            }
            try {
                return Type.valueOf(databaseValue);
            } catch (IllegalArgumentException e) {
                // Handle invalid enum values from the database (e.g., log, return a default)
                System.err.println("Invalid Type in database: " + databaseValue);
                return null; // Or a default value
            }
        }

        @Override
        public String convertToDatabaseValue(Type entityProperty) {
            return entityProperty == null ? null : entityProperty.name();
        }
    }
}