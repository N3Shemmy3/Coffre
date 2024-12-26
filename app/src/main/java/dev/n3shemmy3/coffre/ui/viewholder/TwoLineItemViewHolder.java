package dev.n3shemmy3.coffre.ui.viewholder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import dev.n3shemmy3.coffre.R;
import dev.n3shemmy3.coffre.ui.item.ListItem;


public class TwoLineItemViewHolder extends SingleLineItemViewHolder {

    public final TextView itemSubTitle;

    public TwoLineItemViewHolder(@NonNull View itemView) {
        super(itemView);
        this.itemSubTitle = itemView.findViewById(R.id.itemSubTitle);
    }

    @Override
    public void onBindViewHolder(ListItem item) {
        super.onBindViewHolder(item);
        itemSubTitle.setText(item.getSubTitle());
    }

    @NonNull
    public static TwoLineItemViewHolder create(@NonNull ViewGroup parent) {
        return new TwoLineItemViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_two_line, parent, false));
    }
}
