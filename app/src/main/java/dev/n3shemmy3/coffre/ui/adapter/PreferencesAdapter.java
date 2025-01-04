package dev.n3shemmy3.coffre.ui.adapter;

import android.annotation.SuppressLint;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceGroupAdapter;
import androidx.preference.PreferenceViewHolder;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.ShapeAppearanceModel;

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
        Preference current = getItem(position);

        //Heart of the card separation code
        if (holder.itemView instanceof MaterialCardView) {
            MaterialCardView cardView = (MaterialCardView) holder.itemView;
            float radius = holder.itemView.getResources().getDimension(R.dimen.material_card_corner_radius);

            // Check if the current preference has a parent category
            boolean isInCategory = current.getParent() instanceof PreferenceCategory;

            // Determine if the current item is the first or last within the category
            boolean isFirstInCategory = isInCategory && isFirstItemInCategory(position);
            boolean isLastInCategory = isInCategory && isLastItemInCategory(position);

            // If the preference is not in a category, treat it as a standalone item
            boolean isStandalone = !isInCategory;

            // Set the corner radii
            float topRadius = (isFirstInCategory || isStandalone) ? radius : 0f;
            float bottomRadius = (isLastInCategory || isStandalone) ? radius : 0f;

            // Apply the corner radius to the MaterialCardView
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

    private boolean isFirstItemInCategory(int position) {
        for (int i = position - 1; i >= 0; i--) {
            Preference previous = getItem(i);
            if (previous instanceof PreferenceCategory) {
                return true; // Current item is the first after the category header
            }
        }
        return false;
    }

    private boolean isLastItemInCategory(int position) {
        for (int i = position + 1; i < getItemCount(); i++) {
            Preference next = getItem(i);
            if (next instanceof PreferenceCategory) {
                return true; // Current item is the last before the next category
            }
        }
        return position == getItemCount() - 1; // Check if it's the last item in the list
    }


}
