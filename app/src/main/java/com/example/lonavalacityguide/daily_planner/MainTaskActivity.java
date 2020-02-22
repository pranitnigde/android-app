package com.example.lonavalacityguide.daily_planner;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.lonavalacityguide.R;

import java.util.List;

public class MainTaskActivity extends AppCompatActivity {
    private FloatingActionButton fabAddTask;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_task);

        recyclerView=findViewById(R.id.recyclerviewtasks);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fabAddTask=findViewById(R.id.floatingActionButton);
        fabAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainTaskActivity.this,AddTaskActivity.class));

            }
        });
        getTasks();
    }

    private void getTasks() {
        class GetTasks extends AsyncTask<Void,Void,List<Task>>{


            @Override
            protected List<Task> doInBackground(Void... voids) {
                List<Task> taskList=DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .taskDao()
                        .getAll();
                return  taskList;
            }

            @Override
            protected void onPostExecute(List<Task> tasks) {
                super.onPostExecute(tasks);
                TaskAdapter adapter=new TaskAdapter(MainTaskActivity.this,tasks);
                recyclerView.setAdapter(adapter);
            }
        }

        GetTasks gt=new GetTasks();
        gt.execute();
    }
}
