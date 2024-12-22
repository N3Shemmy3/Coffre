package dev.n3shemmy3.coffre.ui.viewholder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import dev.n3shemmy3.coffre.R;

/**
 * A simple two line list item.
 */
public class TwoLineItemViewHolder extends SingleLineItemViewHolder {

    public final TextView itemSubTitle;

    public TwoLineItemViewHolder(@NonNull View itemView) {
        super(itemView);
        this.itemSubTitle = itemView.findViewById(R.id.itemSubTitle);

        itemIcon.setImageResource(R.drawable.outline_local_cafe_24);
        itemTitle.setText("Item title");
        itemSubTitle.setText("Item Supporting");
    }

    @NonNull
    public static TwoLineItemViewHolder create(@NonNull ViewGroup parent) {
        return new TwoLineItemViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_two_line, parent, false));
    }
}
