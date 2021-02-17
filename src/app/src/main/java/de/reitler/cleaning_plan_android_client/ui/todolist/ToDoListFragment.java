package de.reitler.cleaning_plan_android_client.ui.todolist;

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

import de.reitler.cleaning_plan_android_client.R;

public class ToDoListFragment extends Fragment {

    private ToDoListViewModel toDoListViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toDoListViewModel =
                new ViewModelProvider(this).get(ToDoListViewModel.class);
        View root = inflater.inflate(R.layout.fragment_todolist, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        toDoListViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}