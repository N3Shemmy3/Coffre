package dev.n3shemmy3.coffre.ui.adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import dev.n3shemmy3.coffre.backend.item.Currency;
import dev.n3shemmy3.coffre.ui.interfaces.ItemListener;
import dev.n3shemmy3.coffre.ui.item.TwoLineItem;

public class CurrencyAdapter extends ListAdapter<Currency, TwoLineItem> {


    private ItemListener<Currency> itemListener;

    public CurrencyAdapter() {
        super(new DiffUtil.ItemCallback<>() {

            @Override
            public boolean areItemsTheSame(@NonNull Currency oldItem, @NonNull Currency newItem) {
                return oldItem.getName().equals(newItem.getName());
            }

            @Override
            public boolean areContentsTheSame(@NonNull Currency oldItem, @NonNull Currency newItem) {
                return oldItem.equals(newItem);
            }
        });
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
    }
}

