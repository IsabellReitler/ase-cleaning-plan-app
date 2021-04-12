package de.reitler.app.ui.household;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import de.reitler.app.MainActivity;
import de.reitler.app.R;
import de.reitler.app.model.Roommate;
import de.reitler.app.model.Task;
import de.reitler.app.ui.todolist.daily.DailyToDoListAdapter;
import de.reitler.app.viewmodel.MainActivityViewModel;

public class HouseholdFragment extends Fragment {

    private RoommateRecyclerViewAdapter roommateAdapter;
    private TaskRecyclerViewAdapter taskAdapter;
    private MainActivity activity;
    private MainActivityViewModel mViewModel;
    private HouseholdViewModel taskViewModel;
    private View view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        roommateAdapter = new RoommateRecyclerViewAdapter();
        taskAdapter = new TaskRecyclerViewAdapter();
        activity = (MainActivity) getActivity();
        mViewModel = MainActivityViewModel.getInstance(activity.application);
        taskViewModel = new HouseholdViewModel(activity.getApplication());
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_household, container, false);
        RecyclerView roommateView = view.findViewById(R.id.roommates_list);
        roommateView.setLayoutManager(new LinearLayoutManager(getContext()));
        mViewModel.getRoommates().observe(getViewLifecycleOwner(), new Observer<List<Roommate>>() {
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
                for(Task t: tasks){
                    System.out.println(t.getTitle());
                }
            }
        });

        roommateView.setAdapter(roommateAdapter);
        taskView.setAdapter(taskAdapter);
        //Wichtig: Hardcodierten Wert herausnehmen!
        taskViewModel.getAllTasksFromHouseholdLiveData("5c076861-cf30-483c-a953-108e1ec491da");
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        //mViewModel.getRoommatesInfo();
        //taskViewModel.getAllTasksFromHouseholdLiveData(mViewModel.getHousehold().getValue().getId());
    }

    @Override
    public void onResume() {
        super.onResume();
       mViewModel.getRoommatesInfo();
        taskViewModel.getAllTasksFromHouseholdLiveData("5c076861-cf30-483c-a953-108e1ec491da");
    }
}