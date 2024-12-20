package dev.n3shemmy3.coffre.ui.activity;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import dev.n3shemmy3.coffre.R;
import dev.n3shemmy3.coffre.ui.fragment.BaseFragment;
import dev.n3shemmy3.coffre.ui.fragment.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        getWindow().setSharedElementsUseOverlay(false);
        setContentView(R.layout.activity_main);
    }

    public void replaceFragment(@NonNull BaseFragment fragment) {
        replaceFragment(fragment, null, null);
    }

    public void replaceFragment(@NonNull BaseFragment fragment, @NonNull Bundle args) {
        replaceFragment(fragment, null, args);
    }

    public void replaceFragment(@NonNull BaseFragment fragment, @Nullable View sharedElement, @Nullable Bundle args) {
        fragment.setArguments(args);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setReorderingAllowed(true);
        if (args != null)
            transaction.addSharedElement(sharedElement, args.getString("transitionName"));
        transaction
                .addToBackStack(System.currentTimeMillis() + "")
                .replace(R.id.fragmentContainer, fragment)
                .commit();
    }
}