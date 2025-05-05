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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.google.gson.Gson;
import com.robinhood.ticker.TickerUtils;
import com.robinhood.ticker.TickerView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import dev.n3shemmy3.coffre.R;
import dev.n3shemmy3.coffre.backend.entity.Currency;
import dev.n3shemmy3.coffre.backend.viewmodel.MainViewModel;
import dev.n3shemmy3.coffre.ui.base.BaseFragment;
import dev.n3shemmy3.coffre.ui.utils.NumberUtils;
import dev.n3shemmy3.coffre.ui.utils.PrefUtil;

public class MainBalanceCard extends BaseFragment {
    private MainViewModel viewModel;

    private TickerView round;
    private TextView separator;
    private TickerView decimal;
    private TextView textCurrency;
    private TickerView income, expenses;
    private Currency currency;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_main_balance_card;
    }

    @Override
    public void onCreateFragment(@NonNull View root, @Nullable Bundle savedInstanceState) {
        round = root.findViewById(R.id.round);
        separator = root.findViewById(R.id.separator);
        decimal = root.findViewById(R.id.decimal);
        income = root.findViewById(R.id.income);
        expenses = root.findViewById(R.id.expenses);
        textCurrency = root.findViewById(R.id.currency);
        round.setCharacterLists(TickerUtils.provideNumberList());
        decimal.setCharacterLists(TickerUtils.provideNumberList());
        income.setCharacterLists(TickerUtils.provideNumberList());
        expenses.setCharacterLists(TickerUtils.provideNumberList());
        currency = new Gson().fromJson(PrefUtil.getString("currency"), Currency.class);
        textCurrency.setText(currency.getSymbol().isEmpty() ? currency.getCode() : currency.getSymbol());
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        viewModel.getNetBalance().observe(getViewLifecycleOwner(), netBalance -> {
            BigDecimal formattedBalance, intPart;
            boolean usesDecimals = Integer.parseInt(currency.getDecimal_digits()) > 0;
            formattedBalance = NumberUtils.formatAmount(currency, netBalance);
            intPart = formattedBalance.setScale(0, usesDecimals ? RoundingMode.DOWN : RoundingMode.HALF_UP);
            DecimalFormat formatter = new DecimalFormat("#,###");
            round.setText(String.valueOf(formatter.format(intPart)));
            decimal.setText(String.valueOf(NumberUtils.getAbsDecimalPart(formattedBalance)).replaceFirst("^0.", ""));
            if (!usesDecimals) {
                decimal.setVisibility(View.GONE);
                separator.setVisibility(View.GONE);
            }
        });

        viewModel.getTotalIncome().observe(getViewLifecycleOwner(), incomeBalance ->
                income.setText(NumberUtils.formatCurrency(currency, incomeBalance))
        );
        viewModel.getTotalExpenses().observe(getViewLifecycleOwner(), expenseBalance ->
                expenses.setText(NumberUtils.formatCurrency(currency, expenseBalance))
        );

    }
}
