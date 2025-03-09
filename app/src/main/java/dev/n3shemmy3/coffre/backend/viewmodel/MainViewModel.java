package dev.n3shemmy3.coffre.backend.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import dev.n3shemmy3.coffre.backend.item.Transaction;

public class MainViewModel extends ViewModel {
    private final MutableLiveData<List<Transaction>> transactions = new MutableLiveData<>();

    public void addItem(Transaction transaction) {
        if (transactions.getValue() == null) transactions.setValue(new ArrayList<>());
        if (transactions.getValue().contains(transaction)) {
            List<Transaction> temp = transactions.getValue();
            temp.set(temp.lastIndexOf(transaction), transaction);
            transactions.setValue(temp);
        } else {
            List<Transaction> temp = transactions.getValue();
            temp.add(transaction);
            transactions.setValue(temp);

        }

    }

    public LiveData<List<Transaction>> getTransactions() {
        return this.transactions;
    }
}