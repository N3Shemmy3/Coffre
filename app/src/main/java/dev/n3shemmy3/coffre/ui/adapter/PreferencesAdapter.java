package dev.n3shemmy3.coffre.ui.adapter;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceGroupAdapter;
import androidx.preference.PreferenceViewHolder;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.datepicker.MaterialCalendar;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.CornerTreatment;
import com.google.android.material.shape.ShapeAppearanceModel;

import java.util.ArrayList;
import java.util.List;

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

        //Heart of the card separation code
        if (holder.itemView instanceof MaterialCardView) {
            MaterialCardView cardView = (MaterialCardView) holder.itemView;
            Preference current = getItem(position);
            float radius = holder.itemView.getResources().getDimension(R.dimen.material_card_corner_radius);

            // Determine if the current item is the first or last
            boolean isFirstItem = position == 0;
            boolean isLastItem = position == getPreferenceList().size() - 1;

            // Determine corner radius based on position
            boolean isTopRounded = isFirstItem || (position > 0 && "separator".equals(getPreferenceList().get(position - 1).getKey()));
            boolean isBottomRounded = isLastItem || (position < getPreferenceList().size() - 1 && "separator".equals(getPreferenceList().get(position + 1).getKey()));

            float topRadius = isTopRounded ? radius : 0f;  // Adjust these values as needed
            float bottomRadius = isBottomRounded ? radius : 0f;

            // Apply corner radius to the CardView
            ShapeAppearanceModel shapeModel = cardView.getShapeAppearanceModel()
                    .toBuilder()
                    .setTopLeftCorner(CornerFamily.ROUNDED, topRadius)
                    .setTopRightCorner(CornerFamily.ROUNDED, topRadius)
                    .setBottomLeftCorner(CornerFamily.ROUNDED, bottomRadius)
                    .setBottomRightCorner(CornerFamily.ROUNDED, bottomRadius)
                    .build();
            cardView.setShapeAppearanceModel(shapeModel);

            // Set visibility for separator items
            if ("separator".equals(current.getKey())) {
                holder.itemView.setVisibility(View.GONE);
            } else {
                holder.itemView.setVisibility(View.VISIBLE);
            }
        }

    }

    public List<Preference> getPreferenceList() {
        List<Preference> preferences = new ArrayList<>();
        int count = getItemCount();

        for (int i = 0; i < count; i++) {
            preferences.add(getItem(i));
        }

        return preferences;
    }


}
