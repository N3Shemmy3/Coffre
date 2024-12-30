package dev.n3shemmy3.coffre.ui.fragment;

import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.MenuItemCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;

import dev.n3shemmy3.coffre.R;
import dev.n3shemmy3.coffre.ui.utils.InsetUtils;

public class ProfileFragment extends BaseFragment {

    private AppBarLayout topAppBar;
    private MaterialToolbar topToolBar;
    //Appbar header views
    private ShapeableImageView appBarHeader, appBarAvatar;
    private TextView appBarExpandedTitle;
    private FloatingActionButton fab;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_profile;
    }

    @Override
    protected void onFragmentCreated(@NonNull View root, @Nullable Bundle savedInstanceState) {
        if (getArguments() != null) {
            String transitionName = getArguments().getString("transitionName");
            ViewCompat.setTransitionName(root, transitionName);
        }
        topAppBar = root.findViewById(R.id.topAppBar);
        topToolBar = root.findViewById(R.id.topToolBar);
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


        InsetUtils.applyDisplayCutoutMargin(root.findViewById(R.id.content), true, false, true, false);
    }

}