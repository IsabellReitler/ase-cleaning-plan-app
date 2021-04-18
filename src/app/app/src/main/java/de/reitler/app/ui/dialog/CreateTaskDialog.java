package de.reitler.app.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.DialogFragment;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import de.reitler.app.MainActivity;
import de.reitler.app.R;
import de.reitler.app.apiservice.RepetitiveTaskCreateBody;
import de.reitler.app.apiservice.SimpleTaskCreateBody;
import de.reitler.app.model.Household;
import de.reitler.app.ui.add_household.AddHouseholdActivity;
import de.reitler.app.ui.household.HouseholdFragment;

public class CreateTaskDialog extends DialogFragment {

    public interface CreateTaskDialogListener{
        public void onDialogPositiveClick(SimpleTaskCreateBody body);
        public void onDialogPositiveClick(RepetitiveTaskCreateBody body);
    }

    private CreateTaskDialogListener listener;
    private EditText title;
    private EditText description;
    private CheckBox repetitive;
    private DatePicker datePicker;
    private EditText timeInterval;
    private CheckBox switchRoommate;
    private View view;
    private MainActivity activity;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        activity = (MainActivity) getActivity();
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.dialog_create_task, null);
        title = view.findViewById(R.id.create_task_title);
        description = view.findViewById(R.id.create_task_description);
        repetitive = view.findViewById(R.id.create_task_repetitive);
        datePicker = view.findViewById(R.id.create_task_endDate);
        repetitive.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                changeView(isChecked);
            }
        });
        builder.setView(view)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                if(repetitive.isChecked()){
                    int days =  Integer.parseInt(timeInterval.getText().toString());
                    listener.onDialogPositiveClick(new RepetitiveTaskCreateBody(title.getText().toString(), description.getText().toString(), days, switchRoommate.isChecked(),activity.userId));
                } else {
                    Date date = new Date(datePicker.getYear()-1900, datePicker.getMonth(), datePicker.getDayOfMonth(), 23,59,59);
                    listener.onDialogPositiveClick(new SimpleTaskCreateBody(title.getText().toString(), description.getText().toString(), date, activity.userId));
                }

            }
        })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        return builder.create();
    }

    public void setNewListDialogListener(CreateTaskDialogListener listener){
        this.listener = listener;
    }

    private Date createDateFromTimeInterval() {
        //ToDo: Prüfen, ob das korrekt ist
        LocalDate date = LocalDate.of(0,1,1);
        int days =  Integer.parseInt(timeInterval.getText().toString());
        date.plusDays(days);
        return Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    private void changeView(boolean isChecked) {
        if(isChecked){
            showTimeInterval();
        } else {
            showDatePicker();
        }
    }

    private void showDatePicker() {
        ((ViewGroup)view).addView(datePicker);
        ((ViewGroup)view).removeView(timeInterval);
        ((ViewGroup)view).removeView(switchRoommate);
    }

    private void showTimeInterval() {
        timeInterval = view.findViewById(R.id.create_task_time_interval);
        switchRoommate = view.findViewById(R.id.create_task_switch_roommate);
        ((ViewGroup)view).removeView(datePicker);
        timeInterval.setVisibility(View.VISIBLE);
        switchRoommate.setVisibility(View.VISIBLE);
    }
}
