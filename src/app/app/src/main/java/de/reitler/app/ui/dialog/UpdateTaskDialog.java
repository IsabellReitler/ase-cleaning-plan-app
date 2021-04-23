package de.reitler.app.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import java.time.LocalDate;
import java.time.Year;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import de.reitler.app.MainActivity;
import de.reitler.app.R;
import de.reitler.app.apiservice.RepetitiveTaskCreateBody;
import de.reitler.app.apiservice.SimpleTaskCreateBody;
import de.reitler.app.model.Task;

public class UpdateTaskDialog extends DialogFragment {

    public interface UpdateTaskDialogListener {
        public void onDialogPositiveClick(Task body);

    }


    private UpdateTaskDialog.UpdateTaskDialogListener listener;
    private EditText title;
    private EditText description;
    private DatePicker datePicker;
    private EditText timeInterval;
    private CheckBox switchRoommate;
    private View view;
    private TextView timeIntervalLabel;
    private TextView switchRoommateLabel;
    private MainActivity activity;
    private Task task;



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        activity = (MainActivity) getActivity();
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.dialog_update_task, null);
        title = view.findViewById(R.id.update_task_title);
        description = view.findViewById(R.id.update_task_description);
        datePicker = view.findViewById(R.id.update_task_endDate);
        timeInterval = view.findViewById(R.id.update_task_time_interval);
        switchRoommate = view.findViewById(R.id.update_task_switch_roommate);
        timeIntervalLabel = view.findViewById(R.id.update_task_time_interval_label);
        switchRoommateLabel = view.findViewById(R.id.update_task_switch_roommate_label);
        if(task.getTimeInterval()== 0) {
            this.title.setText(task.getTitle());
            this.description.setText(task.getDescription());
            Calendar deadline = Calendar.getInstance();
            deadline.setTime(task.getDeadline());
            this.datePicker.init(deadline.get(Calendar.YEAR),  deadline.get(Calendar.MONTH), deadline.get(Calendar.DAY_OF_MONTH),dateChangedListener);
            ((ViewGroup) view.findViewById(R.id.update_task_time_interval_layout)).removeView(timeInterval);
            ((ViewGroup) view.findViewById(R.id.update_task_switch_roommate_layout)).removeView(switchRoommate);
            ((ViewGroup) view.findViewById(R.id.update_task_time_interval_layout)).removeView(timeIntervalLabel);
            ((ViewGroup) view.findViewById(R.id.update_task_switch_roommate_layout)).removeView(switchRoommateLabel);
        }else{
            this.title.setText(task.getTitle());
            this.description.setText(task.getDescription());
            this.switchRoommate.setChecked(task.isSwitchRoommate());
            this.timeInterval.setText(""+task.getTimeInterval());
            ((ViewGroup) view).removeView(datePicker);
        }
        builder.setView(view)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        task.setTitle(title.getText().toString());
                        task.setDescription(description.getText().toString());
                        if(!timeInterval.getText().toString().contentEquals("")){
                            task.setTimeInterval( Integer.parseInt(timeInterval.getText().toString()));
                        }
                        if(switchRoommate != null){
                            task.setSwitchRoommate(switchRoommate.isChecked());
                        }
                        listener.onDialogPositiveClick(task);
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        return builder.create();
    }

    public void setNewListDialogListener(UpdateTaskDialog.UpdateTaskDialogListener listener) {
        this.listener = listener;
    }


    private void showDatePicker() {
        ((ViewGroup) view.findViewById(R.id.create_task_time_interval_layout)).removeView(timeInterval);
        ((ViewGroup) view.findViewById(R.id.create_task_switch_roommate_layout)).removeView(switchRoommate);
        ((ViewGroup) view.findViewById(R.id.create_task_time_interval_layout)).removeView(timeIntervalLabel);
        ((ViewGroup) view.findViewById(R.id.create_task_switch_roommate_layout)).removeView(switchRoommateLabel);
    }

    private void showTimeInterval() {
        ((ViewGroup) view).removeView(datePicker);
        timeInterval.setVisibility(View.VISIBLE);
        timeIntervalLabel.setVisibility(View.VISIBLE);
        switchRoommate.setVisibility(View.VISIBLE);
        switchRoommateLabel.setVisibility(View.VISIBLE);
    }

    public void setTask(Task task) {
        this.task = task;
    }

    DatePicker.OnDateChangedListener dateChangedListener = new DatePicker.OnDateChangedListener() {
        @Override
        public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            Calendar c = Calendar.getInstance();
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, monthOfYear);
            c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            task.setDeadline(c.getTime());
        }
    };
}
