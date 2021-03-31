package de.reitler.app.ui.todolist;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    CheckBox checked;
    TextView title;
    TextView deadline;

    public CheckBox getChecked() {
        return checked;
    }

    public void setChecked(CheckBox checked) {
        this.checked = checked;
    }

    public TextView getTitle() {
        return title;
    }

    public void setTitle(TextView title) {
        this.title = title;
    }

    public TextView getDeadline() {
        return deadline;
    }

    public void setDeadline(TextView deadline) {
        this.deadline = deadline;
    }

    public RecyclerViewHolder(CheckBox checkBox, TextView textView, TextView deadline, View view){
        super(view);
        this.checked = checkBox;
        this.title = textView;
        this.deadline = deadline;
    }

}
