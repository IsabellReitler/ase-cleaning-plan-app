package de.reitler.app.ui.todolist.daily;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import de.reitler.app.MainActivity;
import de.reitler.app.R;
import de.reitler.app.apiservice.HttpAdapter;
import de.reitler.app.model.Task;

public class DailyToDoListFragment extends Fragment {

    private DailyToDoListViewModel mViewModel;

    public static DailyToDoListFragment newInstance() {
        return new DailyToDoListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_daily_todolist, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MainActivity activity = (MainActivity) this.getActivity();
        HttpAdapter httpAdapter = new HttpAdapter();
       // List<Task> tasks = httpAdapter.getAllDailyTasksFromRoommate(activity.viewModel.getUser().getValue().getId());
        mViewModel = new ViewModelProvider(this).get(DailyToDoListViewModel.class);
       // mViewModel.setTasks(tasks);
        // TODO: Use the ViewModel
    }

}