package dev.n3shemmy3.coffre.ui.adapter;
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

import android.text.format.DateFormat;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.google.gson.Gson;

import java.util.Calendar;

import dev.n3shemmy3.coffre.R;
import dev.n3shemmy3.coffre.backend.entity.Currency;
import dev.n3shemmy3.coffre.backend.entity.Transaction;
import dev.n3shemmy3.coffre.ui.interfaces.ItemListener;
import dev.n3shemmy3.coffre.ui.item.TwoLineItem;
import dev.n3shemmy3.coffre.ui.utils.DateUtils;
import dev.n3shemmy3.coffre.ui.utils.CurrencyUtils;
import dev.n3shemmy3.coffre.ui.utils.PrefUtil;

public class TransactionsAdapter extends ListAdapter<Transaction, TwoLineItem> {

    private Currency currency;
    private ItemListener<Transaction> itemListener;
    private final boolean useCardStyle;

    public TransactionsAdapter() {
        super(new TransactionsDiffCallback());
        useCardStyle = false;
    }

    public TransactionsAdapter(boolean useCardStyle) {
        super(new TransactionsDiffCallback());
        this.useCardStyle = useCardStyle;
    }

    public void setItemListener(ItemListener<Transaction> itemListener) {
        this.itemListener = itemListener;
    }

    @NonNull
    @Override
    public TwoLineItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        currency = new Gson().fromJson(PrefUtil.getString("currency"), Currency.class);
        return TwoLineItem.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull TwoLineItem holder, int position) {
        Transaction transaction = getItem(position);
        boolean is24HourFormat = DateFormat.is24HourFormat(holder.itemView.getContext());
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(transaction.getTime());
        holder.itemStartIcon.setImageResource(R.drawable.outline_credit_card_24);
        holder.itemTitle.setText(transaction.getTitle());
        holder.itemSubTitle.setText(DateUtils.formatTime(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), is24HourFormat));

        holder.itemEndText.setText(CurrencyUtils.formatCurrency(currency, transaction.getAmount()));
        holder.setEndCardColor(transaction.getType());


        if (itemListener != null) {
            holder.itemView.setOnClickListener(v -> itemListener.onItemClicked(holder.itemView, transaction, position));
            holder.itemView.setOnLongClickListener(v -> {
                itemListener.onItemLongClicked(holder.itemView, transaction, position);
                return true;
            });
        }
        if (useCardStyle) holder.setCardStyle();
    }

    public static class TransactionsDiffCallback extends DiffUtil.ItemCallback<Transaction> {
        @Override
        public boolean areItemsTheSame(@NonNull Transaction oldTransaction, @NonNull Transaction newTransaction) {
            return oldTransaction.getId() == newTransaction.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Transaction oldTransaction, @NonNull Transaction newTransaction) {
            return oldTransaction.equals(newTransaction);
        }
    }
}
