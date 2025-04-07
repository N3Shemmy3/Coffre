package dev.n3shemmy3.coffre.backend.reposity;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import dev.n3shemmy3.coffre.backend.dao.TransactionDao;
import dev.n3shemmy3.coffre.backend.database.TransactionDatabase;
import dev.n3shemmy3.coffre.backend.item.Transaction;

public class TransactionRepository {

    private final TransactionDao transactionDao;
    private final LiveData<List<Transaction>> transactions;
    private final LiveData<BigDecimal> netBalance;
    private final LiveData<BigDecimal> totalExpenses;
    private final LiveData<BigDecimal> totalIncome;

    public TransactionRepository(Application application) {
        TransactionDatabase transactionDatabase = TransactionDatabase.getInstance(application);
        transactionDao = transactionDatabase.transactionDao();
        transactions = transactionDao.getAllTransactions();
        netBalance = transactionDao.getNetBalance();
        totalIncome = transactionDao.getTotalIncome();
        totalExpenses = transactionDao.getTotalExpenses();
    }

    public void insert(Transaction transaction) {
        Executors.newSingleThreadExecutor().execute(() -> transactionDao.insert(transaction));
    }

    public void update(Transaction transaction) {
        Executors.newSingleThreadExecutor().execute(() -> transactionDao.update(transaction));
    }

    public void delete(Transaction transaction) {
        Executors.newSingleThreadExecutor().execute(() -> transactionDao.delete(transaction));
    }

    public void deleteAllTransactions() {
        Executors.newSingleThreadExecutor().execute(transactionDao::deleteAllTransactions);
    }

    public LiveData<List<Transaction>> getAllTransactions() {
        return transactions;
    }

    public LiveData<BigDecimal> getNetBalance() {
        return netBalance;
    }

    public LiveData<BigDecimal> getTotalExpenses() {
        return totalExpenses;
    }

    public LiveData<BigDecimal> getTotalIncome() {
        return totalIncome;
    }
}
