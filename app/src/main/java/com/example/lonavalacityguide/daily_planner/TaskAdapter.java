package com.example.lonavalacityguide.daily_planner;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lonavalacityguide.R;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    private Context context;
    private List<Task> taskList;

    public TaskAdapter(Context context, List<Task> taskList) {
        this.context = context;
        this.taskList = taskList;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.tasks_layout,viewGroup,false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder viewHolder, int i) {
        Task task=taskList.get(i);
        viewHolder.textViewTask.setText(task.getTask());
        viewHolder.textViewDesc.setText(task.getDesc());
        viewHolder.textViewFinishBy.setText(task.getFinishBY());

        if(task.isFinished()){
            viewHolder.textViewStatus.setText("Completed");
        }else
            viewHolder.textViewStatus.setText("Not completed");

    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    class TaskViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textViewStatus, textViewTask, textViewDesc, textViewFinishBy;
        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewStatus=itemView.findViewById(R.id.textViewStatus);
            textViewTask=itemView.findViewById(R.id.textViewTask);
            textViewDesc=itemView.findViewById(R.id.textViewDesc);
            textViewFinishBy=itemView.findViewById(R.id.textViewFinishBy);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            Task task=taskList.get(getAdapterPosition());
            Intent intent=new Intent(context,UpdateTaskActivity.class);
            intent.putExtra("task", task);
            context.startActivity(intent);

        }
    }
}
