package de.reitler.app.ui.household;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import de.reitler.app.ui.todolist.RecyclerViewHolder;

public class TaskRecyclerViewAdapter extends RecyclerView.Adapter<TaskViewHolder>{

    ViewModel viewModel;

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
