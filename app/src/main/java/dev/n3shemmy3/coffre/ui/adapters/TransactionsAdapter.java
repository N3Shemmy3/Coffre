package dev.n3shemmy3.coffre.ui.adapters;

import android.text.format.DateFormat;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import java.text.DecimalFormat;
import java.util.Calendar;

import dev.n3shemmy3.coffre.R;
import dev.n3shemmy3.coffre.backend.item.Transaction;
import dev.n3shemmy3.coffre.ui.interfaces.ItemListener;
import dev.n3shemmy3.coffre.ui.item.TwoLineItem;
import dev.n3shemmy3.coffre.ui.utils.DateUtils;

public class TransactionsAdapter extends ListAdapter<Transaction, TwoLineItem> {


    private ItemListener<Transaction> itemListener;

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

    public void setItemListener(ItemListener<Transaction> itemListener) {
        this.itemListener = itemListener;
    }

    @NonNull
    @Override
    public TwoLineItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return TwoLineItem.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull TwoLineItem holder, int position) {
        Transaction transaction = getItem(position);
        boolean is24HourFormat = DateFormat.is24HourFormat(holder.itemView.getContext());
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(transaction.getTime());
        holder.itemStartIcon.setImageResource(R.drawable.outline_local_cafe_24);
        holder.itemTitle.setText(transaction.getTitle());
        holder.itemSubTitle.setText(DateUtils.formatTime(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), is24HourFormat));
        holder.itemEndText.setText("$" + new DecimalFormat("#.00").format(transaction.getAmount()));
        holder.setEndCardColor(transaction.getTransactionType());


        if (itemListener != null) {
            holder.itemView.setOnClickListener(v -> itemListener.onItemClicked(holder.itemView, transaction, position));
            holder.itemView.setOnLongClickListener(v -> {
                itemListener.onItemLongClicked(holder.itemView, transaction, position);
                return true;
            });
        }
    }
}
