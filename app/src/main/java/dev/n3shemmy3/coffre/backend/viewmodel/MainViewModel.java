package dev.n3shemmy3.coffre.backend.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.math.BigDecimal;
import java.util.List;

import dev.n3shemmy3.coffre.backend.item.Transaction;
import dev.n3shemmy3.coffre.backend.reposity.TransactionRepository;

public class MainViewModel extends AndroidViewModel {

    private final TransactionRepository repository;
    private final LiveData<List<Transaction>> transactions;
    private final MutableLiveData<List<Transaction>> filteredList = new MutableLiveData<>();
    private final LiveData<BigDecimal> netBalance;
    private final LiveData<BigDecimal> totalExpenses;
    private final LiveData<BigDecimal> totalIncome;

    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = new TransactionRepository(application);
        transactions = repository.getAllTransactions();
        netBalance = repository.getNetBalance();
        totalIncome = repository.getTotalIncome();
        totalExpenses = repository.getTotalExpenses();
    }

    public void insert(Transaction item) {
        repository.insert(item);
    }

    public void update(Transaction item) {
        repository.update(item);
    }

    public void delete(Transaction item) {
        repository.delete(item);
    }

    public void deleteAllTransactions() {
        repository.deleteAllTransactions();
    }

    public void search(String query) {
        if (query.isEmpty()) if (filteredList.getValue() != null) filteredList.getValue().clear();
        else filteredList.setValue(repository.search(query).getValue());
    }

    public LiveData<List<Transaction>> getFilteredList() {
        return this.filteredList;
    }

    public LiveData<List<Transaction>> getTransactions() {
        return this.transactions;
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