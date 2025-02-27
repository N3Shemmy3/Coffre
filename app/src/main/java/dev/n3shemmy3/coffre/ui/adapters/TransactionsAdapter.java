package dev.n3shemmy3.coffre.ui.adapters;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import dev.n3shemmy3.coffre.backend.item.Transaction;
import dev.n3shemmy3.coffre.ui.item.TwoLineItem;

public class TransactionsAdapter extends ListAdapter<Transaction, TwoLineItem> {


    public TransactionsAdapter() {
        super(new DiffUtil.ItemCallback<>() {
            @Override
            public boolean areItemsTheSame(@NonNull Transaction oldTransaction, @NonNull Transaction newTransaction) {
                return oldTransaction.getId() == newTransaction.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull Transaction oldTransaction, @NonNull Transaction newTransaction) {
                return oldTransaction.equals(newTransaction);
            }
        });
    }

    @NonNull
    @Override
    public TwoLineItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return TwoLineItem.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull TwoLineItem holder, int position) {
        Transaction transaction = getItem(position);
        holder.itemIcon.setImageResource(holder.itemView.getContext().getResources().getIdentifier("outline_local_cafe_24", "drawable", holder.itemView.getContext().getPackageName()));
        holder.itemTitle.setText(transaction.getTitle());
        holder.itemSubTitle.setText(transaction.getDescription());
        holder.itemEndText.setText("$" + transaction.getAmount());
        holder.setEndCardColor(transaction.getTransactionType());
    }
}
