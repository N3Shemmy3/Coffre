package dev.n3shemmy3.coffre.backend.item;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.math.BigDecimal;
import java.util.Objects;

@Entity(tableName = Transaction.TABLE_NAME)
public class Transaction implements Parcelable {

    public static final String TABLE_NAME = "transactions_table";
    public enum TransactionType {
        INCOME,
        EXPENSE,
        TRANSFER
    }

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String description;
    private BigDecimal amount;
    private TransactionType transactionType;
    private int accountId;
    private long time;

    public Transaction() {
    }

    public Transaction(String title, String description, BigDecimal amount, int transactionType, int accountId, long time) {
        this.title = title;
        this.description = description;
        this.amount = amount;
        this.transactionType = TransactionType.values()[transactionType];
        this.accountId = accountId;
        this.time = time;
    }


    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
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

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    protected Transaction(Parcel in) {
        id = in.readInt();
        title = in.readString();
        description = in.readString();
        amount = BigDecimal.valueOf(in.readDouble());
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
                ", transactionType=" + transactionType +
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
                transactionType == that.transactionType;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + Objects.hashCode(getTitle());
        result = 31 * result + Objects.hashCode(getDescription());
        result = 31 * result + Double.hashCode(getAmount().doubleValue());
        result = 31 * result + getAccountId();
        result = 31 * result + getTransactionType().ordinal();
        result = 31 * result + Objects.hashCode(getTime());
        result = 31 * result + Objects.hashCode(getTransactionType());
        return result;
    }

}
