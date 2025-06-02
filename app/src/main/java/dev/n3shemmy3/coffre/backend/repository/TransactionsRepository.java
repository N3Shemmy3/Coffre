/*
 *
 *  * Copyright (C) 2025 Shemmy
 *  *
 *  * This program is free software: you can redistribute it and/or modify
 *  * it under the terms of the GNU General Public License as published by
 *  * the Free Software Foundation, version 3 of the License.
 *  *
 *  * This program is distributed in the hope that it will be useful,
 *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  * GNU General Public License for more details.
 *  *
 *  * You should have received a copy of the GNU General Public License
 *  * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 */

package dev.n3shemmy3.coffre.backend.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import java.math.BigDecimal;
import java.util.List;

import dev.n3shemmy3.coffre.backend.entity.Amount;
import dev.n3shemmy3.coffre.backend.entity.Transaction;
import dev.n3shemmy3.coffre.backend.entity.Transaction_;
import dev.n3shemmy3.coffre.backend.objectbox.ObjectBox;
import dev.n3shemmy3.coffre.ui.utils.DateUtils;
import io.objectbox.Box;
import io.objectbox.android.ObjectBoxLiveData;
import io.objectbox.query.Query;
import io.objectbox.query.QueryBuilder;

public class TransactionsRepository {

    private static final int PAGE_SIZE = 10;

    private final Box<Transaction> transactionBox;
    private final Box<Amount> amountBox;

    private volatile LiveData<List<Transaction>> pagedTransactions;
    private final MediatorLiveData<BigDecimal> netBalance = new MediatorLiveData<>();
    private final MediatorLiveData<BigDecimal> totalIncome = new MediatorLiveData<>();
    private final MediatorLiveData<BigDecimal> totalExpenses = new MediatorLiveData<>(); // Renamed

    public TransactionsRepository() {
        transactionBox = ObjectBox.get().boxFor(Transaction.class);
        amountBox = ObjectBox.get().boxFor(Amount.class);
        initializeLiveData();
    }

    private void initializeLiveData() {
        pagedTransactions = getTransactions();
        // Amount Calculation with Stream API
        // Instead of iterating and manually summing, use Java's Stream API for a more concise and readable approach.
        netBalance.addSource(new ObjectBoxLiveData<>(transactionBox.query().build()), transactions -> {
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

    public LiveData<List<Transaction>> getTransactions() {
        if (pagedTransactions == null) {
            Query<Transaction> query = transactionBox.query().orderDesc(Transaction_.time).build();
            pagedTransactions = new ObjectBoxLiveData<>(query);

        }
        return pagedTransactions;
    }

    public LiveData<List<Transaction>> search(String query) {
        Query<Transaction> searchQuery = transactionBox.query()
                .contains(Transaction_.title, query, QueryBuilder.StringOrder.CASE_INSENSITIVE) // Case-insensitive title search
                .or().contains(Transaction_.description, query, QueryBuilder.StringOrder.CASE_INSENSITIVE) // Case-insensitive description search
                .orderDesc(Transaction_.time)
                .build();
        return new MutableLiveData<>(searchQuery.find());
    }

    public void insert(Transaction transaction) {
        transactionBox.put(transaction);
        // The LiveData source will automatically update, no need for manual updates here.
    }

    public void delete(Transaction transaction) {
        transactionBox.remove(transaction);
        // LiveData source handles updates.
    }

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