package de.reitler.app.ui.todolist.daily;

import androidx.lifecycle.Observer;

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
import de.reitler.app.viewmodel.MainActivityViewModel;

public class DailyToDoListFragment extends Fragment {

    private MainActivityViewModel mViewModel;
    private DailyToDoListAdapter adapter;
    private View view;
    MainActivity activity;

    public static DailyToDoListFragment newInstance() {
        return new DailyToDoListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new DailyToDoListAdapter();
        activity = (MainActivity) getActivity();
        mViewModel = MainActivityViewModel.getInstance(activity.application);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_daily_todolist, container, false);
        RecyclerView tasksView = view.findViewById(R.id.daily_recyclerview);
        tasksView.setLayoutManager(new LinearLayoutManager(getContext()));
        mViewModel.getDailyTask().observe(getViewLifecycleOwner(), new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                adapter.setTasks(tasks);
                System.out.println("Tasks "+tasks);
            }
        });
        mViewModel.getDailyTasksInfo(activity.userId);
        tasksView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mViewModel.getDailyTasksInfo(activity.userId);
    }

    @Override
    public void onResume() {
        super.onResume();
        mViewModel.getDailyTasksInfo(activity.userId);
    }
}