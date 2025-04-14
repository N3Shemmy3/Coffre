package dev.n3shemmy3.coffre.repository;
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

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import java.math.BigDecimal;

import dev.n3shemmy3.coffre.backend.item.Amount;
import dev.n3shemmy3.coffre.backend.item.Transaction;
import dev.n3shemmy3.coffre.backend.item.Transaction_;
import dev.n3shemmy3.coffre.backend.objectbox.ObjectBox;
import io.objectbox.Box;
import io.objectbox.android.ObjectBoxDataSource;
import io.objectbox.query.Query;
import io.objectbox.query.QueryBuilder;

public class TransactionsRepository {

    private static final int PAGE_SIZE = 10;

    private final Box<Transaction> transactionBox;
    private final Box<Amount> amountBox;

    private LiveData<PagedList<Transaction>> pagedTransactions;
    private final MediatorLiveData<BigDecimal> netBalance = new MediatorLiveData<>();
    private final MediatorLiveData<BigDecimal> totalIncome = new MediatorLiveData<>();
    private final MediatorLiveData<BigDecimal> totalExpenses = new MediatorLiveData<>(); // Renamed

    public TransactionsRepository(@NonNull Application application) {
        ObjectBox.init(application);
        transactionBox = ObjectBox.get().boxFor(Transaction.class);
        amountBox = ObjectBox.get().boxFor(Amount.class);
        initializeLiveData();
    }

    private void initializeLiveData() {
        // 1. Replace observeForever with observe
        // Use the LifecycleOwner (e.g., a Fragment or Activity) to manage the observer's lifecycle.
        // This prevents memory leaks and ensures updates only when the UI is active.
        // Example (assuming this repository is used within a ViewModel):
        //    transactionsRepository.getTransactions().observe(lifecycleOwner, transactions -> { ... });
        // Remove the observeForever call below as it's no longer needed.
        // getTransactions().observeForever(new Observer<PagedList<Transaction>>() { ... });

        // 2. Simplify Amount Calculation with Stream API
        // Instead of iterating and manually summing, use Java's Stream API for a more concise and readable approach.
        netBalance.addSource(getTransactions(), transactions -> {
            if (transactions != null) {
                BigDecimal income = transactions.stream()
                        .filter(transaction -> transaction.getAmount() != null && transaction.getType() == Transaction.Type.INCOME)
                        .map(Transaction::getAmount)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

                BigDecimal expenses = transactions.stream()
                        .filter(transaction -> transaction.getAmount() != null && transaction.getType() == Transaction.Type.EXPENSE)
                        .map(Transaction::getAmount)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

                totalIncome.setValue(income);
                totalExpenses.setValue(expenses);
                netBalance.setValue(income.subtract(expenses));
            } else {
                totalIncome.setValue(BigDecimal.ZERO);
                totalExpenses.setValue(BigDecimal.ZERO);
                netBalance.setValue(BigDecimal.ZERO);
            }
        });
    }

    // 3. Remove Unused Method
    // The onAmountsChanged method is no longer used with the Stream API approach.  Remove it.
    // private void onAmountsChanged(List<Amount> amounts) { ... }

    public LiveData<PagedList<Transaction>> getTransactions() {
        if (pagedTransactions == null) {
            Query<Transaction> query = transactionBox.query().order(Transaction_.time).build();
            pagedTransactions = new LivePagedListBuilder<>(new ObjectBoxDataSource.Factory<>(query), PAGE_SIZE).build();
        }
        return pagedTransactions;
    }

    // 4. Implement Search Functionality
    // The current search method doesn't actually filter transactions.  You need to modify the query
    // to include a where clause that filters based on the query string.  For example, if you want
    // to search by description, you could do something like:
    public LiveData<PagedList<Transaction>> search(String query) {
        Query<Transaction> searchQuery = transactionBox.query()
                .contains(Transaction_.title, query, QueryBuilder.StringOrder.CASE_INSENSITIVE) // Case-insensitive title search
                .or().contains(Transaction_.description, query, QueryBuilder.StringOrder.CASE_INSENSITIVE) // Case-insensitive description search
                .order(Transaction_.time)
                .build();
        return new LivePagedListBuilder<>(new ObjectBoxDataSource.Factory<>(searchQuery), PAGE_SIZE).build();
    }

    public void insert(Transaction transaction) {
        transactionBox.put(transaction);
        // The LiveData source will automatically update, no need for manual updates here.
    }

    // 5. Remove updateTotals Method
    // This method is no longer used with the Stream API calculation.
    // private void updateTotals(Amount amount, BigDecimal income, BigDecimal expense) { ... }

    // 6. Remove fetchAndUpdateAmounts Method
    // This method is also redundant now.
    // private void fetchAndUpdateAmounts() { ... }

    public void delete(Transaction transaction) {
        transactionBox.remove(transaction);
        // LiveData source handles updates.
    }

    // 7. Remove calculateNetBalance Method
    // The net balance is calculated directly within the LiveData source.
    // private void calculateNetBalance() { ... }

    public void deleteAllTransactions() {
        transactionBox.removeAll();
        amountBox.removeAll();
        // LiveData source handles updates.
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