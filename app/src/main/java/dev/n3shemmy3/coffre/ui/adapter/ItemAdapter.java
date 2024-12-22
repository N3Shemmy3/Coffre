package dev.n3shemmy3.coffre.ui.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import dev.n3shemmy3.coffre.R;
import dev.n3shemmy3.coffre.ui.diffutils.BaseItemDiffUtil;
import dev.n3shemmy3.coffre.ui.items.BaseItem;
import dev.n3shemmy3.coffre.ui.items.SingleLineItem;
import dev.n3shemmy3.coffre.ui.viewholder.BaseViewHolder;
import dev.n3shemmy3.coffre.ui.viewholder.ItemViewHolder;
import dev.n3shemmy3.coffre.ui.viewholder.SingleLineItemViewHolder;
import dev.n3shemmy3.coffre.ui.viewholder.TwoLineItemViewHolder;

public class ItemAdapter extends ListAdapter<BaseItem, TwoLineItemViewHolder> {

    public ItemAdapter() {
        super(new BaseItemDiffUtil());
    }

    @NonNull
    @Override
    public TwoLineItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return TwoLineItemViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull TwoLineItemViewHolder holder, int position) {

    }


}
