package dev.n3shemmy3.coffre.ui.viewholder;


import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;

import dev.n3shemmy3.coffre.R;
import dev.n3shemmy3.coffre.ui.item.ListItem;


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
        //start icon
        {

            String icon = item.getIcon();
            if (icon.startsWith("http://") || icon.startsWith("https://")) {
                //load image from url
                Glide.with(itemIcon.getContext()).load(icon).into(itemIcon);
            } else {
                // Load image from resources
                @SuppressLint("DiscouragedApi") int resId = itemIcon.getContext().getResources().getIdentifier(icon, "drawable", itemIcon.getContext().getPackageName());
                itemIcon.setImageResource(resId);
            }
        }
        //end icon & text
        if (item.getEndIcon() != null) {
            String endIcon = item.getIcon();
            if (endIcon.startsWith("http://") || endIcon.startsWith("https://")) {
                //load image from url
                Glide.with(itemEndIcon.getContext()).load(endIcon).into(itemIcon);
            } else {
                // Load image from resources
                @SuppressLint("DiscouragedApi") int resId = itemIcon.getContext().getResources().getIdentifier(endIcon, "drawable", itemIcon.getContext().getPackageName());
                itemEndIcon.setImageResource(resId);
            }
        } else {
            itemEndText.setText(item.getTitle());
        }

    }

    @NonNull
    public static SingleLineItemViewHolder create(@NonNull ViewGroup parent) {
        return new SingleLineItemViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_single_line, parent, false));
    }
}

