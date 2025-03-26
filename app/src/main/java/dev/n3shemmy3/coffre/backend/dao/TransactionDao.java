package dev.n3shemmy3.coffre.backend.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.OnConflictStrategy;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

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

    @Query("DELETE FROM " + Transaction.TABLE_NAME)
    void deleteAllTransactions();

    @Query("SELECT * FROM " + Transaction.TABLE_NAME)
    LiveData<List<Transaction>> getAllTransactions();

}