package de.reitler.app.ui.todolist;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    CheckBox checked;
    TextView title;
    TextView deadline;

    public RecyclerViewHolder(CheckBox checkBox, TextView textView, TextView deadline, View view){
        super(view);
        this.checked = checkBox;
        this.title = textView;
        this.deadline = deadline;
    }
}
