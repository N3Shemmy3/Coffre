package dev.n3shemmy3.coffre.ui.screen.category;

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

import dev.n3shemmy3.coffre.R;
import dev.n3shemmy3.coffre.ui.base.BaseScreen;
import dev.n3shemmy3.coffre.ui.interfaces.TextChangedListener;
import dev.n3shemmy3.coffre.ui.utils.AppUtils;

public class CategoryScreen extends BaseScreen {

    private MenuItem searchItem;
    private LinearLayout searchBar;
    private EditText searchView;
    private SearchView.OnQueryTextListener queryTextListener;

    @Override
    protected int getLayoutResId() {
        return R.layout.screen_category;
    }

    @Override
    protected void onCreateScreen(View root, Bundle state) {
        super.onCreateScreen(root, state);
        searchBar = root.findViewById(R.id.searchBar);
        searchView = root.findViewById(R.id.searchView);

        setUpSearchBar();
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
            if (searchBar.getVisibility() == View.VISIBLE)
                showSearchBar(false);
            else
                navigateUp();

        });
        topToolBar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.action_search)
                showSearchBar(true);
            return true;
        });
        clearButton.setOnClickListener(view -> {
            searchView.setText("");
            //AppUtils.showSoftInput(requireActivity(), searchView, true);
        });
        searchView.addTextChangedListener(new TextChangedListener<>(searchView) {
            @Override
            public void onTextChanged(EditText target, Editable s) {
                clearButton.setVisibility(s.length() > 2 ? View.VISIBLE : View.INVISIBLE);
                //Do stuff
            }
        });
        searchView.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                AppUtils.showSoftInput(requireActivity(), searchView, false);
                if (!TextUtils.isEmpty(v.getText().toString())) {
                    //Text entered
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
}
