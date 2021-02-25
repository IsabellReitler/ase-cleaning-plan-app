package de.reitler.app.ui.todolist;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    CheckBox checked;
    TextView title;

    public RecyclerViewHolder(CheckBox checkBox, TextView textView, View view){
        super(view);
        this.checked = checkBox;
        this.title = textView;
    }
}