package de.reitler.app.ui.todolist.daily;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import de.reitler.app.R;
import de.reitler.app.model.Task;
import de.reitler.app.repositories.TaskRepository;
import de.reitler.app.ui.todolist.RecyclerViewHolder;

public class DailyToDoListAdapter extends RecyclerView.Adapter<RecyclerViewHolder>{

    List<Task> tasks = new ArrayList<>();
    private TaskRepository taskRepo;
    public static final String TIMESTAMP_PATTERN
            = "dd.MM. HH:mm";
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat(TIMESTAMP_PATTERN);

    public void setTasks(List<Task> tasks){
        this.tasks = tasks;
        notifyDataSetChanged();
    }

    public DailyToDoListAdapter(){
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item_todolist, parent, false);
        taskRepo = new TaskRepository();
        CheckBox checkBox = itemView.findViewById(R.id.item_checkbox);
        TextView text = itemView.findViewById(R.id.item_text);
        TextView deadline = itemView.findViewById(R.id.item_deadline);
        return new RecyclerViewHolder(checkBox, text, deadline, itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        boolean done = tasks.get(position).getDoneAt()==null?false:true;
        holder.getChecked().setChecked(done);
        holder.getTitle().setText(tasks.get(position).getTitle());
        if (tasks.get(position).getDeadline() != null)
            holder.getDeadline().setText(dateFormat.format(tasks.get(position).getDeadline()));
       holder.getChecked().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               Task t = tasks.get(position);
               if(holder.getChecked().isChecked() == true){
                   t.setDoneAt(new Date());
               } else{
                   t.setDoneAt(null);
               }
               taskRepo.updateTask(t);

           }
       });

    }

    @Override
    public int getItemCount() {
        return this.tasks.size();
    }
}
