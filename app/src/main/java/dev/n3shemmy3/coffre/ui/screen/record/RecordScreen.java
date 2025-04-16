package dev.n3shemmy3.coffre.ui.screen.record;
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

import static android.text.InputType.TYPE_CLASS_NUMBER;
import static android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL;
import static android.text.InputType.TYPE_NUMBER_FLAG_SIGNED;

import android.os.Bundle;
import android.text.InputFilter;
import android.text.format.DateFormat;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.chip.Chip;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;
import com.google.gson.Gson;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Locale;

import dev.n3shemmy3.coffre.R;
import dev.n3shemmy3.coffre.backend.item.Currency;
import dev.n3shemmy3.coffre.backend.item.Transaction;
import dev.n3shemmy3.coffre.backend.viewmodel.MainViewModel;
import dev.n3shemmy3.coffre.ui.base.BaseScreen;
import dev.n3shemmy3.coffre.ui.utils.DateUtils;
import dev.n3shemmy3.coffre.ui.utils.DecimalDigitsInputFilter;
import dev.n3shemmy3.coffre.ui.utils.InsetsUtils;
import dev.n3shemmy3.coffre.ui.utils.NumberUtils;
import dev.n3shemmy3.coffre.ui.utils.PrefUtil;

public class RecordScreen extends BaseScreen {

    private MainViewModel viewModel;
    private Transaction item = new Transaction();
    private TextInputEditText inputTitle;
    private TextView textCurrency;
    private TextInputEditText inputAmount;
    private TabLayout tabLayout;
    private Chip chipTime;
    private Chip chipDate;
    private TextInputEditText inputNotes;

    private MaterialDatePicker<Long> datePicker;
    private MaterialTimePicker timePicker;
    private Calendar calender;
    private boolean isDeletion = false;
    private Currency currency;

    @Override
    protected int getLayoutResId() {
        return R.layout.screen_record;
    }

    @Override
    protected void onCreateScreen(View root, Bundle savedInstanceState) {
        super.onCreateScreen(root, savedInstanceState);
        inputTitle = root.findViewById(R.id.inputTitle);
        textCurrency = root.findViewById(R.id.textCurrency);
        inputAmount = root.findViewById(R.id.inputAmount);
        tabLayout = root.findViewById(R.id.tabLayout);
        chipTime = root.findViewById(R.id.chipTime);
        chipDate = root.findViewById(R.id.chipDate);
        inputNotes = root.findViewById(R.id.inputNotes);

        currency = new Gson().fromJson(PrefUtil.getString("currency"), Currency.class);
        textCurrency.setText(currency.getSymbol().isEmpty() ? currency.getCode() : currency.getSymbol());
        inputAmount.setInputType(TYPE_NUMBER_FLAG_DECIMAL | TYPE_NUMBER_FLAG_SIGNED | TYPE_CLASS_NUMBER);
        inputAmount.setKeyListener(DigitsKeyListener.getInstance("0123456789,."));
        inputAmount.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(9999999,2)});
        InsetsUtils.applyImeInsets(requireActivity().getWindow(), root);
    }

    @Override
    protected void onScreenCreated(View root, Bundle state) {
        super.onScreenCreated(root, state);
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        Bundle args = getArguments();
        if (args != null) {
            Transaction receivedItem = args.getParcelable("item");
            if (receivedItem != null) {
                item = receivedItem;
                populateFieldsWithItemData(item);
            }
        }
        setUpDateTimePickers();
    }

    private void populateFieldsWithItemData(Transaction item) {
        if (!item.toString().isEmpty()) {
            topToolBar.inflateMenu(R.menu.record_toolbar);
            topToolBar.setOnMenuItemClickListener(menuItem -> {
                if (menuItem.getItemId() == R.id.action_delete) {
                    viewModel.delete(item);
                    isDeletion = true;
                    getScreenManager().popBackStack();
                    return true;
                }

                return false;
            });
        }
        inputTitle.setText(item.getTitle().trim());

        NumberFormat format = NumberFormat.getNumberInstance(Locale.getDefault());
        format.setMinimumFractionDigits(Integer.parseInt(currency.getDecimal_digits()));
        format.setRoundingMode(RoundingMode.DOWN);
        BigDecimal amount = item.getAmount() == null ? BigDecimal.ZERO : item.getAmount();

        inputAmount.setText(String.valueOf(NumberUtils.formatAmount(amount)));

        inputNotes.setText(item.getDescription().trim());
        tabLayout.selectTab(tabLayout.getTabAt(getSelectedTab(item.getType())));
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(item.getTime());
        chipTime.setText(DateUtils.formatTime(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), DateUtils.is24HourFormat(requireContext())));
        chipDate.setText(DateUtils.formatDate(calendar.getTimeInMillis(), requireContext()));
    }

    @Override
    public void onPause() {
        super.onPause();
        if (areInputsEmpty()) return;
        item.setTitle(String.valueOf(inputTitle.getText()).trim());
        item.setDescription(String.valueOf(inputNotes.getText()).trim());
        DecimalFormat decimalFormat = new DecimalFormat("0.00", new DecimalFormatSymbols(Locale.getDefault()));
        decimalFormat.setParseBigDecimal(true);

        String amount = inputAmount.getText() == null ? String.valueOf(BigDecimal.ZERO) : String.valueOf(inputAmount.getText());

        try {
            item.setAmount((BigDecimal) decimalFormat.parse(amount));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        item.setType(Transaction.Type.values()[tabLayout.getSelectedTabPosition()]);
        item.setAccountId(0);
        item.setTime(calender.getTimeInMillis());

        if (!isDeletion) viewModel.insert(item);
    }

    private void setUpDateTimePickers() {
        boolean is24HourFormat = DateFormat.is24HourFormat(requireContext());
        calender = Calendar.getInstance();
        calender.setTimeInMillis(item.getTime() == 0 ? System.currentTimeMillis() : item.getTime());

        CalendarConstraints.Builder constraintsBuilder = new CalendarConstraints.Builder().setStart(0L) // Start from epoch time (optional, ensures full range)
                .setEnd(calender.getTimeInMillis()); // Restrict future dates

        datePicker = MaterialDatePicker.Builder.datePicker().setTitleText(R.string.set_date).setSelection(calender.getTimeInMillis()).setCalendarConstraints(constraintsBuilder.build()) // Apply constraints
                .build();

        datePicker.addOnPositiveButtonClickListener(selection -> {
            if (selection > calender.getTimeInMillis()) {
                selection = calender.getTimeInMillis(); // Prevent setting future date manually
            }

            chipDate.setText(DateUtils.formatDate(selection, requireContext()));
            calender.setTimeInMillis(selection);
            // Reset time if today is selected
            if (DateUtils.isToday(selection)) {
                chipTime.setText(DateUtils.formatTime(calender.get(Calendar.HOUR_OF_DAY), calender.get(Calendar.MINUTE), is24HourFormat));
            }
        });
        timePicker = new MaterialTimePicker.Builder().setTitleText(R.string.set_time).setTimeFormat(is24HourFormat ? TimeFormat.CLOCK_24H : TimeFormat.CLOCK_12H).setHour(calender.get(Calendar.HOUR_OF_DAY)).setMinute(calender.get(Calendar.MINUTE)).build();

        timePicker.addOnPositiveButtonClickListener(picker -> {
            int pickedHour = timePicker.getHour();
            int pickedMinute = timePicker.getMinute();
            calender.set(Calendar.HOUR_OF_DAY, pickedHour);
            calender.set(Calendar.MINUTE, pickedMinute);
            chipTime.setText(DateUtils.formatTime(pickedHour, pickedMinute, is24HourFormat));
            timePicker.setHour(pickedHour);
            timePicker.setMinute(pickedMinute);
        });

        // Set default values using system format
        chipTime.setText(DateUtils.formatTime(calender.get(Calendar.HOUR_OF_DAY), calender.get(Calendar.MINUTE), is24HourFormat));
        chipDate.setText(DateUtils.formatDate(calender.getTimeInMillis(), requireContext()));

        chipDate.setOnClickListener(v -> datePicker.show(getParentFragmentManager(), "DATE_PICKER_TAG"));


        chipTime.setOnClickListener(v -> timePicker.show(getParentFragmentManager(), "TIME_PICKER_TAG"));
    }


    private boolean areInputsEmpty() {
        return inputTitle.getText().toString().isEmpty() || inputAmount.getText().toString().isEmpty();
    }

    private int getSelectedTab(Transaction.Type type) {
        int selection = 0;
        if (type == Transaction.Type.EXPENSE) selection = 1;
        else if (type == Transaction.Type.TRANSFER) selection = 2;
        return selection;
    }

}
