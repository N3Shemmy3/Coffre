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

import android.annotation.SuppressLint;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceGroupAdapter;
import androidx.preference.PreferenceViewHolder;

import dev.n3shemmy3.coffre.R;

@SuppressLint("RestrictedApi")
public class PreferencesAdapter extends PreferenceGroupAdapter {
    public PreferencesAdapter(PreferenceGroup preferenceGroup) {
        super(preferenceGroup);
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    @Override
    public Preference getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @NonNull
    @Override
    public PreferenceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull PreferenceViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (position == 0 && getItemCount() == 1) {
            holder.itemView.setBackgroundResource(R.drawable.item_background_single);
        } else if (position == 0) {
            holder.itemView.setBackgroundResource(R.drawable.item_background_top);
        } else if (position == getItemCount() - 1) {
            holder.itemView.setBackgroundResource(R.drawable.item_background_bottom);
        } else {
            holder.itemView.setBackgroundResource(R.drawable.item_background_middle);
        }
    }
}
