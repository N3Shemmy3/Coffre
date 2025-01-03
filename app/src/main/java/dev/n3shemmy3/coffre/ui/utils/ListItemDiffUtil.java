package dev.n3shemmy3.coffre.ui.utils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import dev.n3shemmy3.coffre.ui.item.ListItem;


public class ListItemDiffUtil extends DiffUtil.ItemCallback<ListItem> {


    @Override
    public boolean areItemsTheSame(@NonNull ListItem oldItem, @NonNull ListItem newItem) {
        return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull ListItem oldItem, @NonNull ListItem newItem) {
        return oldItem.toString().equalsIgnoreCase(newItem.toString());
    }
}

