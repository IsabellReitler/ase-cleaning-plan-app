package de.reitler.app.ui.todolist.daily;

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
import de.reitler.app.viewmodel.MainActivityViewModel;

public class DailyToDoListFragment extends Fragment{

    private DailyToDoListViewModel dailyToDoListViewModel;
    private DailyToDoListAdapter adapter;
    private View view;
    MainActivity activity;
    SwipeRefreshLayout refreshLayout;
    RecyclerView tasksView;

    public static DailyToDoListFragment newInstance() {
        return new DailyToDoListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new DailyToDoListAdapter();
        activity = (MainActivity) getActivity();
        dailyToDoListViewModel = new DailyToDoListViewModel(activity.getApplication());

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_daily_todolist, container, false);
        tasksView = view.findViewById(R.id.daily_recyclerview);
        refreshLayout = view.findViewById(R.id.daily_todolist_refreshlayout);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                dailyToDoListViewModel.updateDailyTasks(activity.userId);
                refreshLayout.setRefreshing(false);
            }
        });
        tasksView.setLayoutManager(new LinearLayoutManager(getContext()));
        dailyToDoListViewModel.getDailyTasks().observe(getViewLifecycleOwner(), new Observer<List<Task>>() {
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
        tasksView.setAdapter(adapter);
        dailyToDoListViewModel.updateDailyTasks(activity.userId);
    }


//    @Override
//    public void onResume() {
//        super.onResume();
//        dailyToDoListViewModel.updateDailyTasks(activity.userId);
//    }
//

}