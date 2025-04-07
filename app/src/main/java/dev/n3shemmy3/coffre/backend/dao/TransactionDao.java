package dev.n3shemmy3.coffre.backend.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.OnConflictStrategy;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.math.BigDecimal;
import java.util.List;

import dev.n3shemmy3.coffre.backend.database.TransactionDatabase;
import dev.n3shemmy3.coffre.backend.item.Transaction;

@Dao
public interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Transaction item);

    @Update
    void update(Transaction item);

    @Delete
    void delete(Transaction item);

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

    @Query("SELECT * FROM " + Transaction.TABLE_NAME + " WHERE (title LIKE '%' || :query || '%' OR description LIKE '%' || :query || '%') AND amount >= :query AND amount <= :query")
    LiveData<List<Transaction>> search(String query);

}