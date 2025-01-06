package dev.n3shemmy3.coffre.ui.adapter;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
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

            // Check if the current item is part of a category
            boolean isPartOfCategory = current.getParent() instanceof PreferenceCategory;

            // Get the category and its children, if applicable
            PreferenceCategory parentCategory = isPartOfCategory ? (PreferenceCategory) current.getParent() : null;
            List<Preference> categoryChildren = isPartOfCategory ? getPreferences(parentCategory) : null;

            // Determine position in the category
            boolean isFirstInCategory = isPartOfCategory && isFirstInCategory(position, categoryChildren);
            boolean isLastInCategory = isPartOfCategory && isLastInCategory(position, categoryChildren);

            // Fallback for standalone items
            boolean isFirstItem = position == 0;
            boolean isLastItem = position == getItemCount() - 1;

            boolean isTopRounded = isPartOfCategory ? isFirstInCategory : isFirstItem;
            boolean isBottomRounded = isPartOfCategory ? isLastInCategory : isLastItem;

            float topRadius = isTopRounded ? radius : 0f;
            float bottomRadius = isBottomRounded ? radius : 0f;

            // Apply the rounded corners to the MaterialCardView
            ShapeAppearanceModel shapeModel = cardView.getShapeAppearanceModel()
                    .toBuilder()
                    .setTopLeftCorner(CornerFamily.ROUNDED, topRadius)
                    .setTopRightCorner(CornerFamily.ROUNDED, topRadius)
                    .setBottomLeftCorner(CornerFamily.ROUNDED, bottomRadius)
                    .setBottomRightCorner(CornerFamily.ROUNDED, bottomRadius)
                    .build();
            cardView.setShapeAppearanceModel(shapeModel);
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

    public List<Preference> getPreferences(PreferenceCategory preferenceCategory) {
        List<Preference> preferences = new ArrayList<>();
        int count = preferenceCategory.getPreferenceCount();

        for (int i = 0; i < count; i++) {
            preferences.add(preferenceCategory.getPreference(i));
        }

        return preferences;
    }
    private boolean isFirstInCategory(int position, List<Preference> categoryChildren) {
        if (categoryChildren == null) return false;

        Preference current = getItem(position);
        return categoryChildren.get(0).equals(current); // First in the category
    }

    private boolean isLastInCategory(int position, List<Preference> categoryChildren) {
        if (categoryChildren == null) return false;

        Preference current = getItem(position);
        return categoryChildren.get(categoryChildren.size() - 1).equals(current); // Last in the category
    }



}
