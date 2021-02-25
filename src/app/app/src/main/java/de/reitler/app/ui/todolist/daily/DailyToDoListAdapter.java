package de.reitler.app.ui.todolist.daily;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import de.reitler.app.ui.todolist.RecyclerViewHolder;

public class DailyToDoListAdapter extends RecyclerView.Adapter<RecyclerViewHolder>{

    ViewModel viewModel;

    public DailyToDoListAdapter(ViewModel viewModel){}

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
