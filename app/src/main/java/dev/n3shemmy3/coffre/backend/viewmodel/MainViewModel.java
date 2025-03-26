package dev.n3shemmy3.coffre.backend.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import dev.n3shemmy3.coffre.backend.item.Transaction;
import dev.n3shemmy3.coffre.backend.reposity.TransactionRepository;

public class MainViewModel extends AndroidViewModel {

    private final TransactionRepository repository;
    private final LiveData<List<Transaction>> transactions;

    public MainViewModel(@NonNull Application application) {
        super(application);

        repository = new TransactionRepository(application);
        transactions = repository.getAllTransactions();
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

    public LiveData<List<Transaction>> getTransactions() {
        return this.transactions;
    }
}