package dev.n3shemmy3.coffre.ui.viewholder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import dev.n3shemmy3.coffre.ui.items.BaseItem;

public class ItemViewHolder extends BaseViewHolder<BaseItem> {
    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public void onBindViewHolder(BaseItem item) {

    }

    public static ItemViewHolder create(@NonNull ViewGroup parent, @LayoutRes int layoutId) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false));
    }
}
