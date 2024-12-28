package dev.n3shemmy3.coffre.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dev.n3shemmy3.coffre.R;

public class ProfileFragment extends BaseFragment {

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
       // disableNestedScrolling((ViewGroup) root.findViewById(R.id.SettingsContainer));
    }

    //basically remove that overscroll effect
    void disableNestedScrolling(ViewGroup viewGroup) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View view = viewGroup.getChildAt(i);
            if (view instanceof RecyclerView) {
                view.setOverScrollMode(View.OVER_SCROLL_NEVER);
                view.setNestedScrollingEnabled(false);
            } else if (view instanceof ViewGroup) {
                disableNestedScrolling(viewGroup);
            } else {
                break;
            }

        }
    }
}