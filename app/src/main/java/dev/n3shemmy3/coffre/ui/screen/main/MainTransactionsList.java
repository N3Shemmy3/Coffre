package dev.n3shemmy3.coffre.ui.screen.main;
/*
 * Copyright (C) 2025 Shemmy
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import dev.n3shemmy3.coffre.R;
import dev.n3shemmy3.coffre.backend.item.Transaction;
import dev.n3shemmy3.coffre.backend.viewmodel.MainViewModel;
import dev.n3shemmy3.coffre.ui.adapters.TransactionsAdapter;
import dev.n3shemmy3.coffre.ui.base.BaseFragment;
import dev.n3shemmy3.coffre.ui.interfaces.ItemListener;
import dev.n3shemmy3.coffre.ui.navigator.Navigator;
import dev.n3shemmy3.coffre.ui.screen.record.RecordScreen;

public class MainTransactionsList extends BaseFragment implements ItemListener<Transaction> {
    private MainViewModel viewModel;
    private RecyclerView recycler;
    private LinearLayoutManager layoutManager;
    private TransactionsAdapter adapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_transactions_list;
    }

    @Override
    public void onCreateFragment(@NonNull View root, @Nullable Bundle savedInstanceState) {
        recycler = root.findViewById(R.id.recycler);
        recycler.setItemAnimator(new DefaultItemAnimator());
        layoutManager = new LinearLayoutManager(getContext());
        recycler.setLayoutManager(layoutManager);
        adapter = new TransactionsAdapter();
        adapter.setItemListener(this);
        recycler.setAdapter(adapter);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        viewModel.getTransactions().observe(getViewLifecycleOwner(), items -> adapter.submitList(items));
    }

    @Override
    public void onItemClicked(@NonNull View itemView, Transaction item, int position) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("item", item);
        Navigator.push(getSupportFragmentManager(), new RecordScreen(), bundle);
    }

    @Override
    public void onItemLongClicked(@NonNull View itemView, Transaction item, int position) {
     //   viewModel.delete(item);
    }
}
