package de.reitler.app.ui.household;

import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;

import de.reitler.app.R;

public class TaskMenuViewHolder extends RecyclerView.ViewHolder {

    public Button edit;
    public Button delete;

    public TaskMenuViewHolder(View itemView) {
        super(itemView);
        edit = itemView.findViewById(R.id.menu_task_edit_button);
        delete = itemView.findViewById(R.id.menu_task_delete_button);
    }
}
