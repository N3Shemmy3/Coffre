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
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.math.BigDecimal;
import java.util.Objects;

import io.objectbox.annotation.Convert;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.converter.PropertyConverter;

@Entity
public class Transaction implements Parcelable {

    public static final String TABLE_NAME = "transactions_table";

    public static enum Type {
        INCOME(0),
        EXPENSE(1),
        TRANSFER(2);
        final int id;

        Type(int id) {
            this.id = id;
        }
    }

    @Id
    private long id;
    private String title;
    private String description;

    @Convert(dbType = String.class, converter = BigDecimalConverter.class)
    private BigDecimal amount;
    @Convert(dbType = Integer.class, converter = TypeConverter.class)
    private Type type;
    private int accountId;
    private long time;

    public Transaction() {
    }

    public Transaction(String title, String description, BigDecimal amount, Type type, int accountId, long time) {
        this.title = title;
        this.description = description;
        this.amount = amount;
        this.type = type;
        this.accountId = accountId;
        this.time = time;
    }


    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }

    protected Transaction(Parcel in) {
        id = in.readLong();
        title = in.readString();
        description = in.readString();
        amount = BigDecimal.valueOf(in.readDouble());
        accountId = in.readInt();
    }

    public static final Creator<Transaction> CREATOR = new Creator<>() {
        @Override
        public Transaction createFromParcel(Parcel in) {
            return new Transaction(in);
        }

        @Override
        public Transaction[] newArray(int size) {
            return new Transaction[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeDouble(amount.doubleValue());
        parcel.writeInt(accountId);
    }

    @NonNull
    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", transactionType=" + type +
                ", accountId=" + accountId +
                ", time=" + time +
                '}';
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Transaction)) return false;

        Transaction that = (Transaction) o;
        return getId() == that.getId()
                &&
                Double.compare(getAmount().doubleValue(), that.getAmount().doubleValue()) == 0
                &&
                getAccountId() == that.getAccountId()
                &&
                Objects.equals(getTitle(), that.getTitle())
                &&
                Objects.equals(getDescription(), that.getDescription())
                &&
                Objects.equals(getTime(), that.getTime())
                &&
                type == that.type;
    }

    @Override
    public int hashCode() {
        long result = getId();
        result = 31 * result + Objects.hashCode(getTitle());
        result = 31 * result + Objects.hashCode(getDescription());
        result = 31 * result + Double.hashCode(getAmount().doubleValue());
        result = 31 * result + getAccountId();
        result = 31 * result + getType().ordinal();
        result = 31 * result + Objects.hashCode(getTime());
        result = 31 * result + Objects.hashCode(getType());
        return (int) result;
    }

    public static class BigDecimalConverter implements PropertyConverter<BigDecimal, String> {

        @Override
        public BigDecimal convertToEntityProperty(String databaseValue) {
            return new BigDecimal(databaseValue);
        }

        @Override
        public String convertToDatabaseValue(BigDecimal entityProperty) {
            return entityProperty.toString();
        }
    }

    public static class TypeConverter implements PropertyConverter<Type, Integer> {
        @Override
        public Type convertToEntityProperty(Integer databaseValue) {
            if (databaseValue == null) {
                return null;
            }
            for (Type role : Type.values()) {
                if (role.id == databaseValue) {
                    return role;
                }
            }
            return Type.INCOME;
        }

        @Override
        public Integer convertToDatabaseValue(Type entityProperty) {
            return entityProperty == null ? null : entityProperty.id;
        }
    }
}
