package dev.n3shemmy3.coffre.ui.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import dev.n3shemmy3.coffre.ui.diffutils.ListItemDiffUtil;
import dev.n3shemmy3.coffre.ui.interfaces.ItemListener;
import dev.n3shemmy3.coffre.ui.item.ListItem;
import dev.n3shemmy3.coffre.ui.viewholder.SingleLineItemViewHolder;
import dev.n3shemmy3.coffre.ui.viewholder.TwoLineItemViewHolder;

public class ListItemAdapter extends ListAdapter<ListItem, RecyclerView.ViewHolder> {


    private ItemListener<ListItem> itemListener;

    public ListItemAdapter() {
        super(new ListItemDiffUtil());
    }

    public ListItemAdapter(ItemListener<ListItem> itemListener) {
        super(new ListItemDiffUtil());
        this.itemListener = itemListener;
    }

    public void setItemListener(ItemListener<ListItem> itemListener) {
        this.itemListener = itemListener;
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getSubTitle() != null ? 1 : 0;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case 1:
                return TwoLineItemViewHolder.create(parent);
            default:
                return SingleLineItemViewHolder.create(parent);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (itemListener != null) {
            holder.itemView.setOnClickListener(v -> {
                itemListener.onItemClicked(v, getItem(position), position);
            });
            holder.itemView.setOnLongClickListener(v -> {
                itemListener.onItemLongClicked(v, getItem(position), position);
                return true;
            });
        }
        switch (getItemViewType(position)) {
            case 1:
                ((TwoLineItemViewHolder) holder).onBindViewHolder(getItem(position));
                break;
            default:
                ((SingleLineItemViewHolder) holder).onBindViewHolder(getItem(position));
        }
    }


}
