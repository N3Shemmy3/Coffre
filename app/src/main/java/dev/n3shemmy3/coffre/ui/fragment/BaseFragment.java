package dev.n3shemmy3.coffre.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.color.MaterialColors;
import com.google.android.material.transition.MaterialSharedAxis;

public abstract class BaseFragment extends Fragment {

    protected BaseFragment() {
    }

    protected abstract int getLayoutResId();

    protected abstract void onFragmentCreated(@NonNull View root, @Nullable Bundle savedInstanceState);


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
        View root = inflater.inflate(getLayoutResId(), container, false);
        onFragmentCreated(root, savedInstanceState);
        return root;
    }

    // https://github.com/material-components/material-components-android/issues/1984#issuecomment-1089710991
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Overlap colors.
        view.setBackgroundColor(MaterialColors.getColor(view, android.R.attr.colorBackground));
        postponeEnterTransition();
        view.getViewTreeObserver().addOnPreDrawListener(() -> {
            startPostponedEnterTransition();
            return true;
        });

    }


}
