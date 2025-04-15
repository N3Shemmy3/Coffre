package dev.n3shemmy3.coffre.ui.adapters;
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

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import dev.n3shemmy3.coffre.backend.item.Currency;
import dev.n3shemmy3.coffre.backend.item.Transaction;
import dev.n3shemmy3.coffre.ui.interfaces.ItemListener;
import dev.n3shemmy3.coffre.ui.item.TwoLineItem;

public class CurrencyAdapter extends ListAdapter<Currency, TwoLineItem> {


    private ItemListener<Currency> itemListener;
    private boolean useCardStyle;

    public CurrencyAdapter() {
        super(new CurrencyDiffCallback());
        useCardStyle = false;
    }

    public CurrencyAdapter(boolean useCardStyle) {
        super(new CurrencyDiffCallback());
        this.useCardStyle = useCardStyle;
    }

    public void setItemListener(ItemListener<Currency> itemListener) {
        this.itemListener = itemListener;
    }

    @NonNull
    @Override
    public TwoLineItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return TwoLineItem.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull TwoLineItem holder, int position) {
        Currency currency = getItem(position);

        holder.itemStartIcon.setVisibility(View.GONE);
        holder.itemStartText.setVisibility(View.VISIBLE);
        holder.itemStartText.setText(currency.getSymbol());
        holder.itemTitle.setText(currency.getCode());
        holder.itemSubTitle.setText(currency.getName());
        holder.endCard.setVisibility(View.GONE);

        if (itemListener != null) {
            holder.itemView.setOnClickListener(v -> itemListener.onItemClicked(holder.itemView, currency, position));
            holder.itemView.setOnLongClickListener(v -> {
                itemListener.onItemLongClicked(holder.itemView, currency, position);
                return true;
            });
        }
        if (useCardStyle) holder.setCardStyle();
    }

    static class CurrencyDiffCallback extends DiffUtil.ItemCallback<Currency> {

        @Override
        public boolean areItemsTheSame(@NonNull Currency oldItem, @NonNull Currency newItem) {
            return oldItem.getName().equals(newItem.getName());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Currency oldItem, @NonNull Currency newItem) {
            return oldItem.equals(newItem);
        }

    }
}
