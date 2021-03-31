package de.reitler.app.ui.todolist.weekly;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import de.reitler.app.MainActivity;
import de.reitler.app.R;
import de.reitler.app.model.Task;
import de.reitler.app.ui.todolist.daily.DailyToDoListAdapter;
import de.reitler.app.viewmodel.MainActivityViewModel;

public class WeeklyToDoListFragment extends Fragment {

    private MainActivityViewModel mViewModel;
    private WeeklyToDoListAdapter adapter;
    private View view;
    MainActivity activity;

    public static WeeklyToDoListFragment newInstance() {
        return new WeeklyToDoListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new WeeklyToDoListAdapter();
        activity = (MainActivity) getActivity();
        mViewModel = MainActivityViewModel.getInstance(activity.application);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_weekly_todolist, container, false);
        RecyclerView tasksView = view.findViewById(R.id.weekly_recyclerview);
        tasksView.setLayoutManager(new LinearLayoutManager(getContext()));
        mViewModel.getWeeklyTask().observe(getViewLifecycleOwner(), new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                adapter.setTasks(tasks);
                System.out.println("Tasks "+tasks);
            }
        });
        mViewModel.getWeeklyTaskInfo(activity.userId);
        tasksView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mViewModel.getWeeklyTaskInfo(activity.userId);
    }

    @Override
    public void onResume() {
        super.onResume();
        mViewModel.getWeeklyTaskInfo(activity.userId);
    }

}