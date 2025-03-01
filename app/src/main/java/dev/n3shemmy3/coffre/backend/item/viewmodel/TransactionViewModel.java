package dev.n3shemmy3.coffre.backend.item.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import dev.n3shemmy3.coffre.backend.item.Transaction;

public class TransactionViewModel extends ViewModel {
    private final MutableLiveData<Transaction> selectedItem = new MutableLiveData<>();

    public void selectItem(Transaction transaction) {
        selectedItem.setValue(transaction);
    }

    public LiveData<Transaction> getSelectedItem() {
        return selectedItem;
    }
}