package dev.n3shemmy3.coffre.backend.item;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Date;
import java.util.Objects;

public class Transaction implements Parcelable {

    protected Transaction(Parcel in) {
        id = in.readInt();
        title = in.readString();
        description = in.readString();
        amount = in.readDouble();
        accountId = in.readInt();
    }

    public static final Creator<Transaction> CREATOR = new Creator<Transaction>() {
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
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeDouble(amount);
        parcel.writeInt(accountId);
    }

    public enum TransactionType {
        INCOME,
        EXPENSE,
        TRANSFER
    }

    private int id;
    private String title;
    private String description;
    private double amount;
    private TransactionType type;
    private int accountId;
    private Date date;
    public Transaction() {}
    public Transaction(int id, String title, String description, double amount, int type, int accountId, Date date) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.amount = amount;
        this.type = TransactionType.values()[type];
        this.accountId = accountId;
        this.date = date;
    }



    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public TransactionType getTransactionType() {
        return type;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.type = transactionType;
    }
    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
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

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Transaction)) return false;

        Transaction that = (Transaction) o;
        return getId() == that.getId() && Double.compare(getAmount(), that.getAmount()) == 0 && getAccountId() == that.getAccountId() && Objects.equals(getTitle(), that.getTitle()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getDate(), that.getDate()) && type == that.type;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + Objects.hashCode(getTitle());
        result = 31 * result + Objects.hashCode(getDescription());
        result = 31 * result + Double.hashCode(getAmount());
        result = 31 * result + getAccountId();
        result = 31 * result + getTransactionType().ordinal();
        result = 31 * result + Objects.hashCode(getDate());
        result = 31 * result + Objects.hashCode(getTransactionType());
        return result;
    }
}
