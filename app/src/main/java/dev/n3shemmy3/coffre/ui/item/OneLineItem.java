package dev.n3shemmy3.coffre.ui.item;

import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.color.MaterialColors;

import dev.n3shemmy3.coffre.R;
import dev.n3shemmy3.coffre.backend.item.Transaction;

public class OneLineItem extends RecyclerView.ViewHolder {
    public final ImageView itemIcon;
    public final TextView itemTitle;
    public final ImageView itemEndIcon;
    public final TextView itemEndText;
    public final View endCard;


    public OneLineItem(@NonNull View itemView) {
        super(itemView);
        this.itemIcon = itemView.findViewById(R.id.itemIcon);
        this.itemTitle = itemView.findViewById(R.id.itemTitle);
        this.endCard = itemView.findViewById(R.id.itemEndCard);
        this.itemEndIcon = itemView.findViewById(R.id.itemEndIcon);
        this.itemEndText = itemView.findViewById(R.id.itemEndText);
    }


    @NonNull
    public static OneLineItem create(@NonNull ViewGroup parent) {
        return new OneLineItem(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_one_line, parent, false));
    }
    public void setEndCardColor(Transaction.TransactionType type) {
        int color;
        int textColor;
        switch (type) {
            case EXPENSE:
                color = MaterialColors.getColor(itemView, com.google.android.material.R.attr.colorErrorContainer);
                textColor = MaterialColors.getColor(itemView, com.google.android.material.R.attr.colorOnErrorContainer);
                break;
            case TRANSFER:
                color = MaterialColors.getColor(itemView, com.google.android.material.R.attr.colorPrimaryContainer);
                textColor = MaterialColors.getColor(itemView, com.google.android.material.R.attr.colorOnPrimaryContainer);
                break;
            default:
                color = MaterialColors.getColor(itemView, R.attr.colorSuccessContainer);
                textColor = MaterialColors.getColor(itemView, R.attr.colorOnSuccessContainer);
                break;
        }
        endCard.setBackgroundTintList(ColorStateList.valueOf(color));
        itemEndText.setTextColor(textColor);
        itemEndIcon.setImageTintList(ColorStateList.valueOf(textColor));
    }


}
