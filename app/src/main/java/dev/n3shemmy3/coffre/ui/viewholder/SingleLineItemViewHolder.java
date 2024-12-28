package dev.n3shemmy3.coffre.ui.viewholder;


import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import dev.n3shemmy3.coffre.R;
import dev.n3shemmy3.coffre.ui.item.ListItem;
import dev.n3shemmy3.coffre.ui.utils.AppUtils;


public class SingleLineItemViewHolder extends ViewHolder {

    public final ImageView itemIcon;
    public final TextView itemTitle;

    public final ImageView itemEndIcon;
    public final TextView itemEndText;

    public SingleLineItemViewHolder(@NonNull View itemView) {
        super(itemView);
        this.itemIcon = itemView.findViewById(R.id.itemIcon);
        this.itemTitle = itemView.findViewById(R.id.itemTitle);
        this.itemEndIcon = itemView.findViewById(R.id.itemEndIcon);
        this.itemEndText = itemView.findViewById(R.id.itemEndText);
    }


    public void onBindViewHolder(ListItem item) {
        AppUtils.loadIcon(itemIcon, item.getIcon());
        itemTitle.setText(item.getTitle());
        //end icon & text
        if (item.getEndIcon() != null) AppUtils.loadIcon(itemEndIcon, item.getEndIcon());
        else itemEndText.setText(item.getEndText());
    }

    @NonNull
    public static SingleLineItemViewHolder create(@NonNull ViewGroup parent) {
        return new SingleLineItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_single_line, parent, false));
    }
}

