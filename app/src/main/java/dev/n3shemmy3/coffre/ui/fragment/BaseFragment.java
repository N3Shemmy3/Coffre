package dev.n3shemmy3.coffre.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.transition.MaterialSharedAxis;

import dev.n3shemmy3.coffre.R;
import dev.n3shemmy3.coffre.ui.utils.InsetUtils;

public abstract class BaseFragment extends Fragment {


    protected BaseFragment() {
    }

    protected abstract int getLayoutResId();

    protected void onFragmentCreated(@NonNull View root, @Nullable Bundle savedInstanceState) {
        setDefaults(root);
    }

    private View root;
    public AppBarLayout topAppBar;
    public CollapsingToolbarLayout collToolBar;
    public MaterialToolbar topToolBar;
    public View content;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ViewCompat.setTransitionName(root, null);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Enable material transitions.
        setEnterTransition(new MaterialSharedAxis(MaterialSharedAxis.X, /* forward= */ true));
        setReturnTransition(new MaterialSharedAxis(MaterialSharedAxis.X, /* forward= */ false));
        setExitTransition(new MaterialSharedAxis(MaterialSharedAxis.X, /* forward= */ true));
        setReenterTransition(new MaterialSharedAxis(MaterialSharedAxis.X, /* forward= */ false));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(getLayoutResId(), container, false);
        topAppBar = root.findViewById(R.id.topAppBar);
        topToolBar = root.findViewById(R.id.topToolBar);
        collToolBar = root.findViewById(R.id.collToolBar);
        content = root.findViewById(R.id.content);
        //Toolbar
        if (topToolBar != null)
            topToolBar.setNavigationOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStack());

        onFragmentCreated(root, savedInstanceState);

        return root;
    }

    // https://github.com/material-components/material-components-android/issues/1984#issuecomment-1089710991
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Overlap colors.
        view.setBackgroundColor(MaterialColors.getColor(view, android.R.attr.colorBackground));
        /*
        postponeEnterTransition();
        view.getViewTreeObserver().addOnPreDrawListener(() -> {
            startPostponedEnterTransition();
            return true;
        });

         */

    }

    void setDefaults(@NonNull View root) {
        //Appbar
        if (topAppBar != null)
            InsetUtils.applyAppbarInsets(topAppBar, topToolBar, collToolBar);
        //content below Appbar
        if (content != null)
            InsetUtils.applyContentInsets(root.findViewById(R.id.content));
    }


}
