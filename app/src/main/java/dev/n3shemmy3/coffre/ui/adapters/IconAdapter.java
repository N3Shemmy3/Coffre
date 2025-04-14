package dev.n3shemmy3.coffre.ui.adapters;
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
        drawable.setTint(MaterialColors.getColor(holder.itemView, com.google.android.material.R.attr.colorOnSurface));int drawableSize = IconicsUtils.convertDpToPx(holder.itemView.getContext(), 24);
        drawable.setSizeYPx(drawableSize);
        drawable.setSizeXPx(drawableSize);
        holder.itemStartIcon.setImageDrawable(drawable);
        holder.itemTitle.setText(category.getName().substring(4));
        holder.itemTitle.setMaxLines(2);
        holder.itemTitle.setPadding(holder.itemTitle.getPaddingLeft(), holder.itemTitle.getPaddingTop(), drawableSize / 2, holder.itemTitle.getPaddingBottom());
        holder.endCard.setVisibility(ViewGroup.GONE);
    }
}
