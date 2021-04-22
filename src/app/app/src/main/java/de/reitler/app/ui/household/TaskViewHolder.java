package de.reitler.app.ui.household;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import de.reitler.app.R;


public class TaskViewHolder extends RecyclerView.ViewHolder {

    private CheckBox checked;
    private TextView title;
    private TextView description;
    private TextView roommate;
    private CardView container;

    public TaskViewHolder(CheckBox checked, TextView title, TextView description, TextView roommate, View view) {
        super(view);
        this.checked = checked;
        this.title= title;
        this.description = description;
        this.roommate = roommate;
        this.container = view.findViewById(R.id.task_container);
    }

    public CheckBox getChecked() {
        return checked;
    }

    public TextView getTitle() {
        return title;
    }

    public TextView getDescription() {
        return description;
    }

    public TextView getRoommate() {
        return roommate;
    }

    public CardView getContainer() {
        return container;
    }
}
