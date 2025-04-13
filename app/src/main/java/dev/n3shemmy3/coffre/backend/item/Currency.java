package dev.n3shemmy3.coffre.backend.item;

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
