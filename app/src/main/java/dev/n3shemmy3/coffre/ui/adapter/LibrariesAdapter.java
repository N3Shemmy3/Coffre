/*
 *
 *  * Copyright (C) 2025 Shemmy
 *  *
 *  * This program is free software: you can redistribute it and/or modify
 *  * it under the terms of the GNU General Public License as published by
 *  * the Free Software Foundation, version 3 of the License.
 *  *
 *  * This program is distributed in the hope that it will be useful,
 *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  * GNU General Public License for more details.
 *  *
 *  * You should have received a copy of the GNU General Public License
 *  * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 */

package dev.n3shemmy3.coffre.ui.adapter;

import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import java.util.Objects;

import dev.n3shemmy3.coffre.R;
import dev.n3shemmy3.coffre.backend.entity.Library;
import dev.n3shemmy3.coffre.backend.entity.Transaction;
import dev.n3shemmy3.coffre.ui.item.TwoLineItem;
import dev.n3shemmy3.coffre.ui.utils.AppUtils;

public class LibrariesAdapter extends ListAdapter<Library, TwoLineItem> {
    public LibrariesAdapter() {
        super(new LibrariesDiffCallback());
    }

    @NonNull
    @Override
    public TwoLineItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return TwoLineItem.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull TwoLineItem holder, int position) {
        Library library = getItem(position);
        holder.itemStartIcon.setImageResource(R.drawable.outline_auto_awesome_24);
        holder.itemStartText.setTypeface(Typeface.DEFAULT_BOLD);
        holder.itemTitle.setText(library.getName());
        holder.itemSubTitle.setText(library.getDescription());
        holder.itemEndIcon.setVisibility(View.GONE);
        holder.itemEndText.setText(library.getVersion());
        holder.setCardStyle();
        holder.setEndCardColor(Transaction.Type.INCOME);
        holder.item.setOnClickListener(v -> AppUtils.openLink(v.getContext(), library.getWebsite()));
    }

    public static class LibrariesDiffCallback extends DiffUtil.ItemCallback<Library> {

        @Override
        public boolean areItemsTheSame(@NonNull Library oldItem, @NonNull Library newItem) {
            return oldItem.getName().equals(newItem.getName());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Library oldItem, @NonNull Library newItem) {
            return Objects.equals(oldItem, newItem);
        }
    }
}
