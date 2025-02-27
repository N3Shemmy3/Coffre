package dev.n3shemmy3.coffre.ui.main;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dev.n3shemmy3.coffre.R;
import dev.n3shemmy3.coffre.backend.item.Transaction;
import dev.n3shemmy3.coffre.ui.adapters.TransactionsAdapter;
import dev.n3shemmy3.coffre.ui.base.BaseFragment;

public class MainTransactionsList extends BaseFragment {
    private RecyclerView recycler;
    private LinearLayoutManager layoutManager;
    private TransactionsAdapter adapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_main_transactions_list;
    }

    @Override
    protected void onFragmentCreated(@NonNull View root, @Nullable Bundle savedInstanceState) {
        super.onFragmentCreated(root, savedInstanceState);
        recycler = root.findViewById(R.id.recycler);
        recycler.setItemAnimator(new DefaultItemAnimator());
        layoutManager = new LinearLayoutManager(getContext());
        recycler.setLayoutManager(layoutManager);
        adapter = new TransactionsAdapter();
        recycler.setAdapter(adapter);

        ArrayList<Transaction> transactions = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Transaction transaction = new Transaction();
            transaction.setId(i);
            transaction.setTitle("Coffee");
            transaction.setDescription("Drinks");
            transaction.setAmount(5.00);
            transaction.setTransactionType(Transaction.TransactionType.EXPENSE);
            transactions.add(transaction);
        }

        Transaction transaction = new Transaction();
        transaction.setId(transactions.size() - 1);
        transaction.setTitle("Salary");
        transaction.setDescription("Income");
        transaction.setAmount(2000);
        transaction.setTransactionType(Transaction.TransactionType.INCOME);
        transactions.add(transaction);


        transaction = new Transaction();
        transaction.setId(transactions.size() - 1);
        transaction.setTitle("Bank to Cash");
        transaction.setDescription("Transfer");
        transaction.setAmount(1000);
        transaction.setTransactionType(Transaction.TransactionType.TRANSFER);
        transactions.add(transaction);
        adapter.submitList(transactions);
    }
}
