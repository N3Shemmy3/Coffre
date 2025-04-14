package dev.n3shemmy3.coffre.backend.objectbox;

import java.math.BigDecimal;

import io.objectbox.converter.PropertyConverter;

public class BigDecimalConverter implements PropertyConverter<BigDecimal, String> {

    @Override
    public BigDecimal convertToEntityProperty(String databaseValue) {
        return new BigDecimal(databaseValue);
    }

    @Override
    public String convertToDatabaseValue(BigDecimal entityProperty) {
        return entityProperty.toString();
    }
}