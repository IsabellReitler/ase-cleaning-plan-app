package de.reitler.app.ui.household;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import de.reitler.app.R;
import de.reitler.app.model.Roommate;
import de.reitler.app.model.Task;
import de.reitler.app.ui.todolist.RecyclerViewHolder;

public class TaskRecyclerViewAdapter extends RecyclerView.Adapter<TaskViewHolder>{

    private List<Task> tasks = new ArrayList<>();

    public TaskRecyclerViewAdapter(){
       // this.tasks.add(new Task(UUID.randomUUID().toString(), "task", "This is a Task", Calendar.getInstance().getTime(), Calendar.getInstance().getTime(), null, null, false, "1234"));
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_task_viewholder, parent, false);
        CheckBox checkbox = itemView.findViewById(R.id.task_done);
        TextView title = itemView.findViewById(R.id.task_title);
        TextView description = itemView.findViewById(R.id.task_description);
        TextView roommate = itemView.findViewById(R.id.task_roommate);
        return new TaskViewHolder(checkbox, title, description, roommate, itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = tasks.get(position);
        boolean done = task.getDoneAt()==null?false:true;
        holder.getChecked().setChecked(done);
        holder.getTitle().setText(task.getTitle());
        holder.getDescription().setText(task.getDescription());
        holder.getRoommate().setText(task.getRoommateID());
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
        notifyDataSetChanged();
        //public Task(String id, String title, String description, Date startsAt, Date deadline, Date doneAt, Date timeInterval, boolean switchRoommate, String roommate) {
       //s this.tasks.add(new Task(UUID.randomUUID().toString(), "task", "This is a Task", Calendar.getInstance().getTime(), Calendar.getInstance().getTime(), null, null, false, "1234"));
    }
}
