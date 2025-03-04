package dev.n3shemmy3.coffre.ui.screen.iconpicker;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mikepenz.iconics.typeface.library.googlematerial.OutlinedGoogleMaterial;

import java.util.ArrayList;

import dev.n3shemmy3.coffre.R;
import dev.n3shemmy3.coffre.backend.item.Category;
import dev.n3shemmy3.coffre.ui.adapters.IconAdapter;
import dev.n3shemmy3.coffre.ui.base.BaseScreen;
import dev.n3shemmy3.coffre.ui.interfaces.TextChangedListener;
import dev.n3shemmy3.coffre.ui.utils.AppUtils;
import dev.n3shemmy3.coffre.ui.utils.InsetsUtils;

public class IconPickerScreen extends BaseScreen {
    private RecyclerView recycler;
    private MenuItem searchItem;
    private LinearLayout searchBar;
    private EditText searchView;
    private SearchView.OnQueryTextListener queryTextListener;
    private ArrayList<Category> icons;
    private IconAdapter adapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.screen_icon_picker;
    }

    @Override
    protected void onCreateScreen(View root, Bundle state) {
        super.onCreateScreen(root, state);
        searchBar = root.findViewById(R.id.searchBar);
        searchView = root.findViewById(R.id.searchView);
        recycler = root.findViewById(R.id.recycler);

        setUpSearchBar();
        setUpIconList();
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }


    OnBackPressedCallback callback = new OnBackPressedCallback(false) {
        @Override
        public void handleOnBackPressed() {
            if (searchBar.getVisibility() == View.VISIBLE) {
                showSearchBar(false);
            } else {
                navigateUp();
            }
        }
    };

    private void setUpSearchBar() {
        searchItem = topToolBar.getMenu().findItem(R.id.action_search);
        Button clearButton = root.findViewById(R.id.clearButton);

        topToolBar.setNavigationOnClickListener(view -> {
            if (searchBar.getVisibility() == View.VISIBLE) showSearchBar(false);
            else navigateUp();

        });
        topToolBar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.action_search) showSearchBar(true);
            return true;
        });
        clearButton.setOnClickListener(view -> {
            searchView.setText("");
            //AppUtils.showSoftInput(requireActivity(), searchView, true);
        });
        searchView.addTextChangedListener(new TextChangedListener<>(searchView) {
            @Override
            public void onTextChanged(EditText target, Editable s) {
                String query = target.getText().toString().trim();
                boolean startSearch = query.length() > 2;
                clearButton.setVisibility(startSearch ? View.VISIBLE : View.INVISIBLE);
                performSearch(query);

            }
        });
        searchView.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                AppUtils.showSoftInput(requireActivity(), searchView, false);
                if (!TextUtils.isEmpty(v.getText().toString())) {
                    performSearch(String.valueOf(searchView.getText()));
                } else {
                    showSearchBar(false);
                }
            }
            return true;
        });
    }

    private void showSearchBar(boolean visible) {
        if (searchItem == null) return;
        callback.setEnabled(visible);
        if (visible) {
            searchBar.setVisibility(View.VISIBLE);
            topToolBar.getMenu().clear();
            AppUtils.showSoftInput(requireActivity(), searchView, true);
            searchView.requestFocus();
        } else {
            topToolBar.inflateMenu(R.menu.category_top_toolbar);
            AppUtils.showSoftInput(requireActivity(), searchView, false);
            searchBar.setVisibility(View.GONE);
        }

    }

    private void setUpIconList() {
        InsetsUtils.applyContentInsets(recycler);
        recycler.setLayoutManager(new GridLayoutManager(requireActivity(), 2));
        adapter = new IconAdapter();
        icons = new ArrayList<>();
        for (int i = 0; i < OutlinedGoogleMaterial.INSTANCE.getIconCount(); i++) {
            icons.add(new Category(i, 0, OutlinedGoogleMaterial.INSTANCE.getIcons().get(i)));
        }
        adapter.submitList(icons);
        recycler.setAdapter(adapter);
        recycler.setItemAnimator(new DefaultItemAnimator());

    }

    private void performSearch(String query) {
        if (query.isEmpty()) {
            adapter.submitList(new ArrayList<>());
            adapter.submitList(icons);
        } else {
            ArrayList<Category> temp = icons;
            ;
            adapter.submitList(temp);
        }
    }
}
