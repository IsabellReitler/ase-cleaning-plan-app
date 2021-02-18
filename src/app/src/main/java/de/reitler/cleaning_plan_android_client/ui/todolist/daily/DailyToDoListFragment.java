package de.reitler.cleaning_plan_android_client.ui.todolist.daily;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.reitler.cleaning_plan_android_client.R;

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
        mViewModel = new ViewModelProvider(this).get(DailyToDoListViewModel.class);
        // TODO: Use the ViewModel
    }

}