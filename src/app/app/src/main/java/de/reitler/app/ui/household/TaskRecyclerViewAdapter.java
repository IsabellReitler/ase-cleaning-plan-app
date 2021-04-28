package de.reitler.app.ui.household;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import de.reitler.app.R;
import de.reitler.app.model.Roommate;
import de.reitler.app.model.Task;
import de.reitler.app.repositories.HouseholdRepository;
import de.reitler.app.repositories.TaskRepository;
import de.reitler.app.ui.dialog.CreateTaskDialog;
import de.reitler.app.ui.dialog.UpdateTaskDialog;
import de.reitler.app.ui.todolist.RecyclerViewHolder;

public class TaskRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements UpdateTaskDialog.UpdateTaskDialogListener {

    private List<Task> tasks = new ArrayList<>();
    private TaskRepository taskRepo;
    private final int SHOW_MENU = 1;
    private final int HIDE_MENU = 2;
    View view;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == SHOW_MENU) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.menu_task_viewholder, parent, false);
            return new TaskMenuViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recyclerview_task_viewholder, parent, false);
            taskRepo = new TaskRepository();
            CheckBox checkbox = view.findViewById(R.id.task_done);
            TextView title = view.findViewById(R.id.task_title);
            TextView description = view.findViewById(R.id.task_description);
            TextView roommate = view.findViewById(R.id.task_roommate);
            return new TaskViewHolder(checkbox, title, description, roommate, view);
        }
    }

    /**
     * Abfrage, welcher Viewholder gezeigt wird
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        if (tasks.get(position).isShowMenu()) {
            return SHOW_MENU;
        } else {
            return HIDE_MENU;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Task task = tasks.get(position);
        if (holder instanceof TaskViewHolder) {
            //... same as above
            ((TaskViewHolder) holder).getChecked().setOnCheckedChangeListener(null);
            boolean done = task.getDoneAt() == null ? false : true;
            ((TaskViewHolder) holder).getChecked().setChecked(done);
            ((TaskViewHolder) holder).getTitle().setText(task.getTitle());
            ((TaskViewHolder) holder).getDescription().setText(task.getDescription());
            ((TaskViewHolder) holder).getRoommate().setText(task.getRoommateID());
            ((TaskViewHolder) holder).getChecked().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    Task t = tasks.get(position);
                    if (((TaskViewHolder) holder).getChecked().isChecked() == true) {
                        t.setDoneAt(new Date());
                    } else {
                        t.setDoneAt(null);
                    }
                    taskRepo.updateTask(t);
                }
            });
            ((TaskViewHolder) holder).getContainer().setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    showMenu(position);
                    return true;
                }
            });
        }

        if (holder instanceof TaskMenuViewHolder) {
            ((TaskMenuViewHolder) holder).edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editTask(tasks.get(position));
                    closeMenu();
                }
            });
            ((TaskMenuViewHolder) holder).delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        taskRepo.deleteTask(tasks.get(position).getId());
                        tasks.remove(tasks.get(position));
                        setTasks(tasks);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }

    public void showMenu(int position) {
        for (int i = 0; i < tasks.size(); i++) {
            tasks.get(i).setShowMenu(false);
        }
        tasks.get(position).setShowMenu(true);
        notifyDataSetChanged();
    }


    public boolean isMenuShown() {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).isShowMenu()) {
                return true;
            }
        }
        return false;
    }

    public void closeMenu() {
        for (int i = 0; i < tasks.size(); i++) {
            tasks.get(i).setShowMenu(false);
        }
        notifyDataSetChanged();
    }

    private void editTask(Task task){
        UpdateTaskDialog updateTaskDialog = new UpdateTaskDialog();
        updateTaskDialog.setNewListDialogListener(this);
        updateTaskDialog.show(((FragmentActivity) view.getContext()).getSupportFragmentManager(), "UPDATE TASK");
        updateTaskDialog.setTask(task);
    }


    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
        notifyDataSetChanged();
    }

    @Override
    public void onDialogPositiveClick(Task body) {
        taskRepo.updateTask(body);
        notifyDataSetChanged();
    }
}
