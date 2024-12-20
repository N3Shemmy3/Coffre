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

    public TwoLineItemViewHolder(@NonNull View view) {
        super(view);
        this.itemSubTitle = itemView.findViewById(R.id.itemSubTitle);
    }

    @NonNull
    public static TwoLineItemViewHolder create(@NonNull ViewGroup parent) {
        return new TwoLineItemViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_two_line, parent, false));
    }
}
