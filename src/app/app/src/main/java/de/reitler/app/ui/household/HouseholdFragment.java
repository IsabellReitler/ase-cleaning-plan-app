package de.reitler.app.ui.household;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import de.reitler.app.MainActivity;
import de.reitler.app.R;
import de.reitler.app.model.Roommate;
import de.reitler.app.model.Task;
import de.reitler.app.viewmodel.MainActivityViewModel;

public class HouseholdFragment extends Fragment {

    private RoommateRecyclerViewAdapter roommateAdapter;
    private TaskRecyclerViewAdapter taskAdapter;
    private MainActivity activity;
    private RoommatesViewModel roommatesViewModel;
    private TaskViewModel taskViewModel;
    private View view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        roommateAdapter = new RoommateRecyclerViewAdapter();
        taskAdapter = new TaskRecyclerViewAdapter();
        activity = (MainActivity) getActivity();
        roommatesViewModel = new RoommatesViewModel(activity.getApplication());
        taskViewModel = new TaskViewModel(activity.getApplication());
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_household, container, false);
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

        roommateView.setAdapter(roommateAdapter);
        taskView.setAdapter(taskAdapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        roommatesViewModel.updateRoommates(activity.householdId);
        taskViewModel.updateAllTasksFromHousehold(activity.householdId);
    }
}