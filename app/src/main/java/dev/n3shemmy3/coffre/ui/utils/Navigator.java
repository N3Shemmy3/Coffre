package dev.n3shemmy3.coffre.ui.utils;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import dev.n3shemmy3.coffre.R;

public class Navigator {

    public static void push(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
        push(fragmentManager, fragment, null, null);
    }

    public static void push(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @NonNull Bundle args) {
        push(fragmentManager, fragment, null, args);
    }

    public static void push(@IdRes int layoutId, @NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
        push(layoutId, fragmentManager, fragment, null, null);
    }

    public static void push(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @Nullable View sharedElement, @Nullable Bundle args) {
        push(R.id.fragmentContainer, fragmentManager, fragment, sharedElement, args);
    }

    public static void push(@IdRes int layoutId, @NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @Nullable View sharedElement, @Nullable Bundle args) {
        fragment.setArguments(args);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setReorderingAllowed(true);
        if (sharedElement != null)
            transaction.addSharedElement(sharedElement, args.getString("transitionName"));
        transaction
                .addToBackStack(String.valueOf(System.currentTimeMillis()))
                .replace(layoutId, fragment)
                .commit();
    }
}