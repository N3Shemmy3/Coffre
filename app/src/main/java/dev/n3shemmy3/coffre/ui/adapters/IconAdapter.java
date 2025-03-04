package dev.n3shemmy3.coffre.ui.adapters;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.google.android.material.color.MaterialColors;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.typeface.library.googlematerial.OutlinedGoogleMaterial;
import com.mikepenz.iconics.utils.IconicsUtils;

import dev.n3shemmy3.coffre.backend.item.Category;
import dev.n3shemmy3.coffre.ui.item.OneLineItem;

public class IconAdapter extends ListAdapter<Category, OneLineItem> {
    public IconAdapter() {
        super((new DiffUtil.ItemCallback<>() {
            @Override
            public boolean areItemsTheSame(@NonNull Category oldItem, @NonNull Category newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull Category oldItem, @NonNull Category newItem) {
                return oldItem.toString().equals(newItem.toString());
            }
        }));
    }

    @NonNull
    @Override
    public OneLineItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return OneLineItem.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull OneLineItem holder, int position) {
        Category category = getItem(position);
        IconicsDrawable drawable = new IconicsDrawable(holder.itemView.getContext(), OutlinedGoogleMaterial.INSTANCE.getIcon(category.getName()));
        drawable.setTint(MaterialColors.getColor(holder.itemView, com.google.android.material.R.attr.colorOnSurface));
        drawable.setSizeYPx(IconicsUtils.convertDpToPx(holder.itemView.getContext(), 24));
        drawable.setSizeXPx(IconicsUtils.convertDpToPx(holder.itemView.getContext(), 24));
        holder.itemIcon.setImageDrawable(drawable);
        holder.itemTitle.setText(category.getName());
        holder.endCard.setVisibility(ViewGroup.GONE);
    }
}
