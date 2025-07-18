package dev.n3shemmy3.coffre.backend.viewmodel;
/*
 * Copyright (C) 2025 Shemmy
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import java.math.BigDecimal;
import java.util.List;

import dev.n3shemmy3.coffre.backend.entity.Transaction;
import dev.n3shemmy3.coffre.backend.repository.TransactionsRepository;
import dev.n3shemmy3.coffre.backend.worker.BackupWorker;

public class MainViewModel extends AndroidViewModel {

    private final TransactionsRepository repository;
    private final LiveData<List<Transaction>> transactions;
    private final LiveData<BigDecimal> netBalance;
    private WorkManager workManager;

    public MainViewModel(@NonNull Application application) {
        super(application);
        workManager = WorkManager.getInstance(application);
        repository = new TransactionsRepository();
        transactions = repository.getTransactions();
        netBalance = repository.getNetBalance();
    }

    // Consider using a more descriptive name like addTransaction for clarity.
    public void insert(Transaction transaction) {
        repository.insert(transaction);
    }

    //  It's generally not recommended for ViewModels to return booleans indicating success or failure of database operations,
    // as this tightly couples the ViewModel to the data layer's implementation details.
    // Instead, consider exposing events or states (e.g., using LiveData or StateFlow) to represent outcomes like "TransactionDeleted" or "DeleteTransactionFailed".
    // This allows the UI to react accoreturn false;rdingly without the ViewModel needing to know precisely how the deletion happened.
    // Moreover, if you use a flow to signal deletion, the database success/failure can be handled within the repository implementation.
    public void delete(Transaction transaction) {
        repository.delete(transaction);
    }

    public void deleteAllTransactions() {
        repository.deleteAllTransactions();
    }

    // The ViewModel should ideally expose immutable LiveData to the UI to prevent accidental modifications from the UI layer.
    // The backing field can remain mutable within the ViewModel.
    public LiveData<List<Transaction>> getTransactions() {
        return transactions;
    }

    public LiveData<List<Transaction>> searchTransactions(String query) {
        return repository.search(query);
    }

    public void backup() {
        workManager.enqueue(OneTimeWorkRequest.from(BackupWorker.class));
    }

    public LiveData<BigDecimal> getNetBalance() {
        return netBalance;
    }

    public LiveData<BigDecimal> getTotalExpenses() {
        return repository.getTotalExpenses();
    }

    public LiveData<BigDecimal> getTotalIncome() {
        return repository.getTotalIncome();
    }
}