package dev.n3shemmy3.coffre.ui.viewholder;


import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import dev.n3shemmy3.coffre.R;

/**
 * A simple single line list item.
 */
public class SingleLineItemViewHolder extends ViewHolder {

    public final ImageView itemIcon;
    public final TextView itemTitle;

    public SingleLineItemViewHolder(@NonNull View view) {
        super(view);
        this.itemIcon = itemView.findViewById(R.id.itemIcon);
        this.itemTitle = itemView.findViewById(R.id.itemTitle);
    }

    @NonNull
    public static SingleLineItemViewHolder create(@NonNull ViewGroup parent) {
        return new SingleLineItemViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_one_line, parent, false));
    }
}
