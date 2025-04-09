package dev.n3shemmy3.coffre.backend.dao;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.math.BigDecimal;
import java.util.List;

import dev.n3shemmy3.coffre.backend.item.Transaction;

@Dao
public interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Transaction item);

    @Update
    void update(Transaction item);

    @Query("DELETE FROM " + Transaction.TABLE_NAME + " WHERE id = :id")
    int delete(long id);

    @Query("SELECT SUM(CASE WHEN transactionType = 'INCOME' THEN amount ELSE -amount END) FROM " + Transaction.TABLE_NAME)
    LiveData<BigDecimal> getNetBalance();

    @Query("SELECT SUM(amount) FROM " + Transaction.TABLE_NAME + " WHERE transactionType = 'INCOME'")
    LiveData<BigDecimal> getTotalIncome();

    @Query("SELECT SUM(amount) FROM " + Transaction.TABLE_NAME + " WHERE transactionType = 'EXPENSE'")
    LiveData<BigDecimal> getTotalExpenses();

    @Query("DELETE FROM " + Transaction.TABLE_NAME)
    void deleteAllTransactions();

    @Query("SELECT * FROM " + Transaction.TABLE_NAME)
    LiveData<List<Transaction>> getAllTransactions();

    @Query("SELECT * FROM " + Transaction.TABLE_NAME + " WHERE title LIKE '%' || :query || '%'")
    LiveData<List<Transaction>> search(String query);

}