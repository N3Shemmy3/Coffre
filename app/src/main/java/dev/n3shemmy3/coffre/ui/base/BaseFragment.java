package dev.n3shemmy3.coffre.ui.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public abstract class BaseFragment extends Fragment {
    protected BaseFragment() {
    }

    protected abstract int getLayoutResId();

    public void onCreateFragment(@NonNull View root, @Nullable Bundle savedInstanceState){}

    protected View root;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ViewCompat.setTransitionName(root, null);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(getLayoutResId(), container, false);
        onCreateFragment(root, savedInstanceState);
        return root;
    }

    @NonNull
    public FragmentManager getSupportFragmentManager() {
        return requireActivity().getSupportFragmentManager();
    }
}
