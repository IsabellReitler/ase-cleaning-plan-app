package de.reitler.app.ui.todolist.weekly;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.reitler.app.R;
import de.reitler.app.model.Task;
import de.reitler.app.repositories.RoommateRepository;
import de.reitler.app.repositories.TaskRepository;
import de.reitler.app.ui.todolist.RecyclerViewHolder;

public class WeeklyToDoListAdapter extends RecyclerView.Adapter<RecyclerViewHolder>{

    List<Task> tasks = new ArrayList<>();
    private TaskRepository taskRepo;
    private RoommateRepository roommateRepository=RoommateRepository.getInstance();
    public static final String TIMESTAMP_PATTERN
            = "dd.MM.";
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat(TIMESTAMP_PATTERN);
    View itemView;

    public void setTasks(List<Task> tasks){
        this.tasks = tasks;
        notifyDataSetChanged();
    }

    public WeeklyToDoListAdapter(){
    }


    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item_todolist, parent, false);
        taskRepo = new TaskRepository();
        CheckBox checkBox = itemView.findViewById(R.id.item_checkbox);
        TextView text = itemView.findViewById(R.id.item_text);
        TextView deadline = itemView.findViewById(R.id.item_deadline);
        TextView description = itemView.findViewById(R.id.item_description);
        return new RecyclerViewHolder(checkBox, text, deadline, description, itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        boolean done = tasks.get(position).getDoneAt()==null?false:true;
       // holder.getChecked().setOnCheckedChangeListener(null);
        holder.getChecked().setChecked(done);
        holder.getChecked().setClickable(false);
        holder.getTitle().setText(tasks.get(position).getTitle());
        holder.getDescription().setText(tasks.get(position).getDescription());
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Task t = tasks.get(position);
                if(holder.getChecked().isChecked() == true){
                    t.setDoneAt(null);
                } else{
                    t.setDoneAt(new Date());
                }
                taskRepo.updateTask(t);
                roommateRepository.getDailyTasksFromRoommate(t.getId());
                return true;
            }

        });
        if (tasks.get(position).getDeadline() != null)
            holder.getDeadline().setText(dateFormat.format(tasks.get(position).getDeadline()));
    }

    @Override
    public int getItemCount() {
        return this.tasks.size();
    }
}
