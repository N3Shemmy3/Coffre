package dev.n3shemmy3.coffre.ui.item;
/*
 * Copyright (C) 2025 Shemmy
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
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
    public final ImageView itemStartIcon;
    public final TextView itemStartText;
    public final TextView itemTitle;
    public final ImageView itemEndIcon;
    public final TextView itemEndText;
    public final View endCard;


    public OneLineItem(@NonNull View itemView) {
        super(itemView);
        this.itemStartIcon = itemView.findViewById(R.id.itemStartIcon);
        this.itemStartText = itemView.findViewById(R.id.itemStartText);
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
    public void setEndCardColor(Transaction.Type type) {
        int color;
        int textColor;
        switch (type) {
            case EXPENSE:
                color = MaterialColors.getColor(itemView, com.google.android.material.R.attr.colorErrorContainer);
                textColor = MaterialColors.getColor(itemView, com.google.android.material.R.attr.colorOnErrorContainer);
                break;
            case TRANSFER:
                color = MaterialColors.getColor(itemView, com.google.android.material.R.attr.colorTertiaryContainer);
                textColor = MaterialColors.getColor(itemView, com.google.android.material.R.attr.colorOnTertiaryContainer);
                break;
            default:
                color = MaterialColors.getColor(itemView, com.google.android.material.R.attr.colorPrimaryContainer);
                textColor = MaterialColors.getColor(itemView, com.google.android.material.R.attr.colorOnPrimaryContainer);
                break;
        }
        endCard.setBackgroundTintList(ColorStateList.valueOf(color));
        itemEndText.setTextColor(textColor);
        itemEndIcon.setImageTintList(ColorStateList.valueOf(textColor));
    }


}
