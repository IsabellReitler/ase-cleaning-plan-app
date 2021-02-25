package de.reitler.app.ui.todolist.weekly;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.reitler.app.R;

public class WeeklyToDoListFragment extends Fragment {

    private WeeklyToDoListViewModel mViewModel;

    public static WeeklyToDoListFragment newInstance() {
        return new WeeklyToDoListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weekly_todolist, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(WeeklyToDoListViewModel.class);
        // TODO: Use the ViewModel
    }

}