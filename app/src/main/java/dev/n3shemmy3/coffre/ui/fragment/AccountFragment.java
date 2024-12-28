package dev.n3shemmy3.coffre.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.transition.MaterialContainerTransform;

import dev.n3shemmy3.coffre.R;
import dev.n3shemmy3.coffre.ui.utils.InsetUtils;

public class AccountFragment extends BaseFragment {

    private MaterialToolbar topToolBar;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_account;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int colorBackground = MaterialColors.getColor(requireContext(), android.R.attr.colorBackground, Color.TRANSPARENT);

        MaterialContainerTransform transform = new MaterialContainerTransform();
        transform.setAllContainerColors(colorBackground);
        transform.setDuration(getResources().getInteger(com.google.android.material.R.integer.material_motion_duration_long_1));

        setSharedElementEnterTransition(transform);
        setSharedElementReturnTransition(transform);

    }

    @Override
    protected void onFragmentCreated(@NonNull View root, @Nullable Bundle savedInstanceState) {
        super.onFragmentCreated(root, savedInstanceState);
        if (getArguments() != null) {
            String transitionName = getArguments().getString("transitionName");
            ViewCompat.setTransitionName(root, transitionName);
        }
    }
}
