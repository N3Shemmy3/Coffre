package dev.n3shemmy3.coffre.ui.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import dev.n3shemmy3.coffre.R;
import dev.n3shemmy3.coffre.ui.fragment.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) return;
        Toast.makeText(this, "MainActivity state == null", Toast.LENGTH_SHORT).show();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new MainFragment()).commitNow();
    }
}