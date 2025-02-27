package dev.n3shemmy3.coffre.ui.item;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import dev.n3shemmy3.coffre.R;

public class TwoLineItem extends OneLineItem {
    public final TextView itemSubTitle;
    public TwoLineItem(@NonNull View itemView) {
        super(itemView);
        this.itemSubTitle = itemView.findViewById(R.id.itemSubTitle);
    }
    @NonNull
    public static TwoLineItem create(@NonNull ViewGroup parent) {
        return new TwoLineItem(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_two_line, parent, false));
    }
}
