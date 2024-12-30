package dev.n3shemmy3.coffre.ui.fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;

import android.view.View;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;
import com.google.android.material.transition.MaterialContainerTransform;

import java.util.Calendar;
import java.util.Date;

import dev.n3shemmy3.coffre.R;
import dev.n3shemmy3.coffre.ui.utils.InsetUtils;


public class RecordFragment extends BaseFragment {

    private MaterialToolbar topToolBar;

    private TextInputEditText inputTime;
    private TextInputEditText inputDate;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_record;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int colorBackground = MaterialColors.getColor(requireContext(), android.R.attr.colorBackground, Color.TRANSPARENT);

        MaterialContainerTransform transform = new MaterialContainerTransform();
        transform.setAllContainerColors(colorBackground);
        transform.setDuration(getResources().getInteger(com.google.android.material.R.integer.material_motion_duration_long_1));

        setSharedElementEnterTransition(transform);
        setSharedElementReturnTransition(transform);

    }

    @Override
    protected void onFragmentCreated(@NonNull View root, @Nullable Bundle savedInstanceState) {
        super.onFragmentCreated(root, savedInstanceState);
        if (getArguments() != null) {
            String transitionName = getArguments().getString("transitionName");
            ViewCompat.setTransitionName(root, transitionName);
        }
        InsetUtils.applyImeInsets(requireActivity().getWindow(), root.findViewById(R.id.nestedScrollView));
        inflateInputs(root);
    }

    @SuppressLint("ClickableViewAccessibility")
    void inflateInputs(@NonNull View root) {
        inputTime = root.findViewById(R.id.inputTime);
        inputTime.setOnClickListener(v -> {

            MaterialTimePicker timePicker = new MaterialTimePicker.Builder()
                    .setHour(new Date().getHours())
                    .setMinute(new Date().getMinutes())
                    .build();
            timePicker.addOnPositiveButtonClickListener(pickerView -> {
                inputTime.setText(timePicker.getHour() + ":" + timePicker.getMinute());

            });
            timePicker.show(getChildFragmentManager(), "inputTime");
        });

        inputDate = root.findViewById(R.id.inputDate);
        inputDate.setOnClickListener(v -> {
            MaterialDatePicker<Long> datePicker = MaterialDatePicker.Builder.datePicker()
                    .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                    .build();
            datePicker.addOnPositiveButtonClickListener(selection -> {
                inputDate.setText(datePicker.getHeaderText());

            });
            datePicker.show(getChildFragmentManager(), "inputDate");

        });
    }
}
