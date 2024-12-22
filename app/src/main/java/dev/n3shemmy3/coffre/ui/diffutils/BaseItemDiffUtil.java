package dev.n3shemmy3.coffre.ui.diffutils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import dev.n3shemmy3.coffre.ui.items.BaseItem;

public class BaseItemDiffUtil extends DiffUtil.ItemCallback<BaseItem> {

    @Override
    public boolean areItemsTheSame(@NonNull BaseItem oldItem, @NonNull BaseItem newItem) {
        return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull BaseItem oldItem, @NonNull BaseItem newItem) {
        return false;
    }
}