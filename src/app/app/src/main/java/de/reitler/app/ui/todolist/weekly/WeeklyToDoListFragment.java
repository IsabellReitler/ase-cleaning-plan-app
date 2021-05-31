package de.reitler.app.ui.todolist.weekly;

import androidx.lifecycle.Observer;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import de.reitler.app.MainActivity;
import de.reitler.app.R;
import de.reitler.app.model.Task;

public class WeeklyToDoListFragment extends Fragment {

    private WeeklyToDoListViewModel weeklyToDoListViewModel;
    private WeeklyToDoListAdapter adapter;
    private View view;
    MainActivity activity;
    SwipeRefreshLayout swipeRefreshLayout;

    public static WeeklyToDoListFragment newInstance() {
        return new WeeklyToDoListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new WeeklyToDoListAdapter();
        activity = (MainActivity) getActivity();
        weeklyToDoListViewModel = new WeeklyToDoListViewModel(activity.getApplication());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_weekly_todolist, container, false);
        RecyclerView tasksView = view.findViewById(R.id.weekly_recyclerview);
        tasksView.setLayoutManager(new LinearLayoutManager(getContext()));
        swipeRefreshLayout = view.findViewById(R.id.weekly_todolist_refreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                weeklyToDoListViewModel.updateWeeklyTasks(activity.userId);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        weeklyToDoListViewModel.getWeeklyTasks().observe(getViewLifecycleOwner(), new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                adapter.setTasks(tasks);

            }
        });
        tasksView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        weeklyToDoListViewModel.updateWeeklyTasks(activity.userId);
    }

    //    @Override
//    public void onResume() {
//        super.onResume();
//        weeklyToDoListViewModel.updateWeeklyTasks(activity.userId);
//    }

}