package dev.n3shemmy3.coffre.ui.fragment;

import android.os.Bundle;

import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.transition.MaterialElevationScale;

import java.util.ArrayList;

import dev.n3shemmy3.coffre.R;
import dev.n3shemmy3.coffre.ui.activity.MainActivity;
import dev.n3shemmy3.coffre.ui.adapter.ListItemAdapter;
import dev.n3shemmy3.coffre.ui.interfaces.ItemListener;
import dev.n3shemmy3.coffre.ui.item.ListItem;
import dev.n3shemmy3.coffre.ui.utils.InsetUtils;


public class MainFragment extends BaseFragment {


    private RecyclerView transactionRecycler;

    private FloatingActionButton floatingActionButton;


    @Override
    @LayoutRes
    protected int getLayoutResId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void onFragmentCreated(@NonNull View root, @Nullable Bundle savedInstanceState) {
        {
            transactionRecycler = root.findViewById(R.id.transactionRecycler);
            transactionRecycler.setLayoutManager(new LinearLayoutManager(requireContext()));
            transactionRecycler.setItemAnimator(new DefaultItemAnimator());
            ListItemAdapter listItemAdapter = new ListItemAdapter(new ItemListener<>() {
                @Override
                public void onItemClicked(@NonNull View itemView, ListItem item, int position) {
                    MaterialElevationScale exitTransition = new MaterialElevationScale(false);
                    exitTransition.setDuration(getResources().getInteger(com.google.android.material.R.integer.material_motion_duration_long_1));
                    setExitTransition(exitTransition);

                    MaterialElevationScale reenterTransition = new MaterialElevationScale(true);
                    reenterTransition.setDuration(getResources().getInteger(com.google.android.material.R.integer.material_motion_duration_long_1));
                    setReenterTransition(reenterTransition);


                    String transitionName = "shared_element_" + position;
                    ViewCompat.setTransitionName(itemView, transitionName);
                    Bundle bundle = new Bundle();
                    bundle.putString("transitionName", transitionName);
                    ((MainActivity) requireActivity()).replaceFragment(new AccountFragment(), itemView, bundle);

                }

                @Override
                public void onItemLongClicked(@NonNull View itemView, ListItem item, int position) {

                }
            });
            ArrayList<ListItem> items = new ArrayList<>();
            for (int i = 0; i < 12; i++) {
                items.add(new ListItem(i, "outline_local_cafe_24", "Item " + i, "Sub Item " + i));
            }
            listItemAdapter.submitList(items);
            transactionRecycler.setAdapter(listItemAdapter);
        }

        floatingActionButton = root.findViewById(R.id.fab);
        Bundle bundle = new Bundle();
        bundle.putString("transitionName", "fab");
        floatingActionButton.setTransitionName("fab");
        floatingActionButton.setOnClickListener(v -> ((MainActivity) requireActivity()).replaceFragment(new RecordFragment(), floatingActionButton, bundle));


        InsetUtils.applySystemBarsInsets(root.findViewById(R.id.topAppBar), false, true, false, false);
        InsetUtils.applySystemBarsMargin(floatingActionButton, false, false, false, true);
    }


}