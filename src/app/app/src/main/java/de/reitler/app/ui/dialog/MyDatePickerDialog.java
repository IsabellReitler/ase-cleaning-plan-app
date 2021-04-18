package de.reitler.app.ui.dialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

import de.reitler.app.MainActivity;
import de.reitler.app.repositories.RoommateRepository;

public class MyDatePickerDialog extends DialogFragment {

    private RoommateRepository roommateRepository = new RoommateRepository();
    private MainActivity activity ;

    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
            Calendar mCalender = Calendar.getInstance();
            mCalender.set(Calendar.YEAR, year);
            mCalender.set(Calendar.MONTH, month);
            mCalender.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            mCalender.set(Calendar.HOUR_OF_DAY, 23);
            mCalender.set(Calendar.MINUTE, 59);
            mCalender.set(Calendar.SECOND, 59);
            roommateRepository.setHolidayMode(activity.userId,mCalender );
        }
    };
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        activity = (MainActivity) getActivity();
        Calendar mCalender = Calendar.getInstance();
        int year = mCalender.get(Calendar.YEAR);
        int month = mCalender.get(Calendar.MONTH);
        int dayOfMonth = mCalender.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(),listener, year, month, dayOfMonth);
    }
}
