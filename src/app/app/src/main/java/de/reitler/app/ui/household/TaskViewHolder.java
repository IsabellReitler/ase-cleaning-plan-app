package de.reitler.app.ui.household;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TaskViewHolder extends RecyclerView.ViewHolder {

    private TextView title;

    public TaskViewHolder(TextView title, View view) {
        super(view);
        this.title= title;
    }
}
