package de.reitler.app.ui.household;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.List;

import de.reitler.app.MainActivity;
import de.reitler.app.R;
import de.reitler.app.apiservice.RepetitiveTaskCreateBody;
import de.reitler.app.apiservice.SimpleTaskCreateBody;
import de.reitler.app.model.Household;
import de.reitler.app.model.Roommate;
import de.reitler.app.model.Task;
import de.reitler.app.repositories.TaskRepository;
import de.reitler.app.ui.dialog.CreateTaskDialog;
import de.reitler.app.ui.dialog.MyDatePickerDialog;

public class HouseholdFragment extends Fragment implements CreateTaskDialog.CreateTaskDialogListener {

    private RoommateRecyclerViewAdapter roommateAdapter;
    private TaskRecyclerViewAdapter taskAdapter;

    public MainActivity activity;
    private View view;
    private SwipeRefreshLayout refreshLayout;

    private HouseholdFragmentViewModel householdFragmentViewModel;
    private RoommatesViewModel roommatesViewModel;
    private TaskViewModel taskViewModel;

    private Button addRoommateButton;
    private Button addTaskButton;
    private Button setHolidayMode;

    private TaskRepository taskRepository;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        roommateAdapter = new RoommateRecyclerViewAdapter(getContext());
        taskAdapter = new TaskRecyclerViewAdapter();
        taskRepository = new TaskRepository();
        activity = (MainActivity) getActivity();
        roommatesViewModel = new RoommatesViewModel(activity.getApplication());
        taskViewModel = new TaskViewModel(activity.getApplication());
        householdFragmentViewModel = new HouseholdFragmentViewModel(activity.getApplication());
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_household, container, false);
        refreshLayout = view.findViewById(R.id.household_refreshLayout);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                roommatesViewModel.updateRoommates(activity.householdId);
                taskViewModel.updateAllTasksFromHousehold(activity.householdId);
                refreshLayout.setRefreshing(false);
            }
        });
        addRoommateButton = view.findViewById(R.id.add_roommate_button);
        addRoommateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInfoDialog();
            }
        });

        addTaskButton = view.findViewById(R.id.add_task_button);
        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCreateTaskDialog();
            }
        });

        setHolidayMode = view.findViewById(R.id.holiday_mode);
        setHolidayMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCreateHolidayModeDialog();
            }
        });

        RecyclerView roommateView = view.findViewById(R.id.roommates_list);
        roommateView.setLayoutManager(new LinearLayoutManager(getContext()));
        roommatesViewModel.getRoommates().observe(getViewLifecycleOwner(), new Observer<List<Roommate>>() {
            @Override
            public void onChanged(List<Roommate> roommates) {
                roommateAdapter.setRoommates(roommates);
            }
        });

        RecyclerView taskView = view.findViewById(R.id.household_task_list);
        taskView.setLayoutManager(new LinearLayoutManager(getContext()));
        taskViewModel.getAllTasksFromHousehold().observe(getViewLifecycleOwner(), new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                taskAdapter.setTasks(tasks);
            }
        });

        householdFragmentViewModel.getHousehold().observe(getViewLifecycleOwner(), new Observer<Household>() {
            @Override
            public void onChanged(Household household) {
                activity.getSupportActionBar().setTitle(household.getName());
            }
        });
        roommateView.setAdapter(roommateAdapter);
        taskView.setAdapter(taskAdapter);
        householdFragmentViewModel.updateHousehold(activity.householdId);
        return view;
    }

    private void openCreateHolidayModeDialog() {
        MyDatePickerDialog datePicker = new MyDatePickerDialog();
        datePicker.show(activity.getSupportFragmentManager(), "DATE PICK");
    }

    private void openCreateTaskDialog() {
        CreateTaskDialog createTaskDialog = new CreateTaskDialog();
        createTaskDialog.setNewListDialogListener(this);
        createTaskDialog.show(activity.getSupportFragmentManager(), "CREATE TASK");
    }

    private void openInfoDialog() {
        AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
        alertDialog.setTitle("Invite code");
        alertDialog.setMessage(activity.householdId);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    @Override
    public void onResume() {
        super.onResume();
        roommatesViewModel.updateRoommates(activity.householdId);
        taskViewModel.updateAllTasksFromHousehold(activity.householdId);
        householdFragmentViewModel.updateHousehold(activity.householdId);
    }

    @Override
    public void onDialogPositiveClick(SimpleTaskCreateBody body) {
        taskRepository.createTask(body);
        taskViewModel.updateAllTasksFromHousehold(activity.householdId);
    }

    @Override
    public void onDialogPositiveClick(RepetitiveTaskCreateBody body) {
        taskRepository.createTask(body);
        taskViewModel.updateAllTasksFromHousehold(activity.householdId);
    }
}