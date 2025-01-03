package dev.n3shemmy3.coffre.ui.fragment.settings;

import android.os.Bundle;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.FragmentManager;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;

import dev.n3shemmy3.coffre.R;
import dev.n3shemmy3.coffre.ui.fragment.BaseFragment;
import dev.n3shemmy3.coffre.ui.utils.InsetUtils;
import dev.n3shemmy3.coffre.ui.utils.Navigator;

public class SettingsFragment extends BaseSettingsFragment {


    //Appbar header views
    private ShapeableImageView appBarHeader, appBarAvatar;
    private TextView appBarExpandedTitle;
    private FloatingActionButton fab;

    @Override
    public PreferenceFragmentCompat getPreferenceFragment() {
        return new PreferencesFragment();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_settings;
    }

    @Override
    protected void onFragmentCreated(@NonNull View root, @Nullable Bundle savedInstanceState) {
        if (getArguments() != null) {
            String transitionName = getArguments().getString("transitionName");
            ViewCompat.setTransitionName(root, transitionName);
        }
        appBarHeader = root.findViewById(R.id.appBarHeader);
        appBarAvatar = root.findViewById(R.id.appBarAvatar);
        appBarExpandedTitle = root.findViewById(R.id.appBarExpandedTitle);
        fab = root.findViewById(R.id.fab);

        topAppBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(final AppBarLayout appBarLayout, int verticalOffset) {
                //Initialize the size of the scroll
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                //Check if the view is collapsed
                if (scrollRange + verticalOffset == 0) fab.hide();
                else fab.show();

            }
        });


        InsetUtils.applyAppbarInsets(topAppBar, topToolBar, (
                displayCutOutInsets, systemBarInsets) -> {
            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) appBarExpandedTitle.getLayoutParams();

            //add insets to HeaderViews
            int hInsets = displayCutOutInsets.left + displayCutOutInsets.right;
            mlp.leftMargin = hInsets + mlp.leftMargin;
            mlp.rightMargin = hInsets + mlp.rightMargin;
            appBarExpandedTitle.setLayoutParams(mlp);

            //Fab
            mlp = (ViewGroup.MarginLayoutParams) fab.getLayoutParams();
            hInsets = displayCutOutInsets.left + displayCutOutInsets.right;
            mlp.leftMargin = hInsets + mlp.leftMargin;
            mlp.rightMargin = hInsets + mlp.rightMargin;
            fab.setLayoutParams(mlp);
        });

    }

    public static class PreferencesFragment extends BasePreferenceFragment {
        @Override
        public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
            addPreferencesFromResource(R.xml.preferences);
        }

        @Override
        public boolean onPreferenceTreeClick(@NonNull Preference preference) {
            @IdRes int layoutId = R.id.SettingsContainer;
            @NonNull FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            switch (preference.getKey()) {
                case "lookFeel": {
                    Navigator.push(fragmentManager, new LookFeelSettingsFragment());
                    break;
                }
                case "about": {
                    Navigator.push(fragmentManager, new AboutSettingsFragment());
                    break;
                }
            }
            return super.onPreferenceTreeClick(preference);
        }
    }

}