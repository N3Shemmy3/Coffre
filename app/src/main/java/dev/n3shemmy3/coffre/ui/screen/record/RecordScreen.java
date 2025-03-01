package dev.n3shemmy3.coffre.ui.screen.record;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Objects;

import dev.n3shemmy3.coffre.R;
import dev.n3shemmy3.coffre.backend.item.Transaction;
import dev.n3shemmy3.coffre.ui.base.BaseScreen;
import dev.n3shemmy3.coffre.ui.utils.DateUtils;

public class RecordScreen extends BaseScreen {


    private TextInputEditText textTitle;
    private TextInputEditText textAmount;
    private TabLayout tabLayout;
    private AutoCompleteTextView editTime;
    private AutoCompleteTextView editDate;

    @Override
    protected int getLayoutResId() {
        return R.layout.screen_record;
    }

    @Override
    protected void onScreenCreated(View root, Bundle savedInstanceState) {
        super.onScreenCreated(root, savedInstanceState);
        textTitle = root.findViewById(R.id.textTitle);
        textAmount = root.findViewById(R.id.textAmount);
        tabLayout = root.findViewById(R.id.tabLayout);
        editTime = root.findViewById(R.id.editTime);
        editDate = root.findViewById(R.id.editDate);

        setUpDateTimePickers();

        // Change color when selected
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(@NonNull TabLayout.Tab tab) {


            }

            @Override
            public void onTabUnselected(@NonNull TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(@NonNull TabLayout.Tab tab) {
            }
        });
        if (getArguments() != null) {
            getTransaction(getArguments().getParcelable("transaction"));
        }
    }

    private void setUpDateTimePickers() {
        boolean is24HourFormat = DateFormat.is24HourFormat(requireContext());
        Calendar now = DateUtils.getCurrentTime();

        // Set default values using system format
        editTime.setText(DateUtils.formatTime(now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE), is24HourFormat));
        editDate.setText(DateUtils.formatDate(now.getTimeInMillis(), requireContext()));

        // Fix double-click delay issues
        editTime.setOnFocusChangeListener((view, isFocused) -> {
            if (view.isInTouchMode() && isFocused) view.performClick();
        });
        editDate.setOnFocusChangeListener((view, isFocused) -> {
            if (view.isInTouchMode() && isFocused) view.performClick();
        });

        editDate.setOnClickListener(v -> {
            CalendarConstraints.Builder constraintsBuilder = new CalendarConstraints.Builder()
                    .setStart(0L) // Start from epoch time (optional, ensures full range)
                    .setEnd(now.getTimeInMillis()); // Restrict future dates

            MaterialDatePicker<Long> datePicker = MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Select date")
                    .setSelection(now.getTimeInMillis()) // Default to today
                    .setCalendarConstraints(constraintsBuilder.build()) // Apply constraints
                    .build();

            datePicker.addOnPositiveButtonClickListener(selection -> {
                if (selection > now.getTimeInMillis()) {
                    selection = now.getTimeInMillis(); // Prevent setting future date manually
                }

                editDate.setText(DateUtils.formatDate(selection, requireContext()));

                // Reset time if today is selected
                if (DateUtils.isToday(selection)) {
                    editTime.setText(DateUtils.formatTime(now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE), is24HourFormat));
                }
            });

            datePicker.show(getParentFragmentManager(), "DATE_PICKER_TAG");
        });


        editTime.setOnClickListener(v -> {
            Calendar selectedDate = Calendar.getInstance();
            try {
                selectedDate.setTime(Objects.requireNonNull(DateFormat.getDateFormat(requireContext()).parse(editDate.getText().toString())));
            } catch (ParseException e) {
                selectedDate.setTimeInMillis(now.getTimeInMillis());
            }

            boolean isToday = DateUtils.isToday(selectedDate.getTimeInMillis());

            MaterialTimePicker timePicker = new MaterialTimePicker.Builder()
                    .setTimeFormat(is24HourFormat ? TimeFormat.CLOCK_24H : TimeFormat.CLOCK_12H)
                    .setHour(isToday ? now.get(Calendar.HOUR_OF_DAY) : 0)
                    .setMinute(isToday ? now.get(Calendar.MINUTE) : 0)
                    .setTitleText("Select time")
                    .build();

            timePicker.addOnPositiveButtonClickListener(picker -> {
                int pickedHour = timePicker.getHour();
                int pickedMinute = timePicker.getMinute();

                // Prevent selecting future times if today is chosen
                if (isToday && (pickedHour > now.get(Calendar.HOUR_OF_DAY) ||
                        (pickedHour == now.get(Calendar.HOUR_OF_DAY) && pickedMinute > now.get(Calendar.MINUTE)))) {
                    pickedHour = now.get(Calendar.HOUR_OF_DAY);
                    pickedMinute = now.get(Calendar.MINUTE);
                }

                editTime.setText(DateUtils.formatTime(pickedHour, pickedMinute, is24HourFormat));
            });

            timePicker.show(getParentFragmentManager(), "TIME_PICKER_TAG");
        });
    }


    private void getTransaction(Transaction transaction) {
        textTitle.setText(transaction.getTitle());
        textAmount.setText(new DecimalFormat("#.00").format(transaction.getAmount()));
    }
}
