package com.example.lonavalacityguide.daily_planner;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lonavalacityguide.R;

public class AddTaskActivity extends AppCompatActivity {

    public EditText etTask,etDesc,etFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        etTask=findViewById(R.id.etAddTask);
        etDesc=findViewById(R.id.etDescription);
        etFinish=findViewById(R.id.etFinish);

        findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTask();
            }
        });
    }

    private void saveTask() {

        final String task=etTask.getText().toString().trim();
        final String desc=etDesc.getText().toString().trim();
        final String finish=etFinish.getText().toString().trim();

        if(task.isEmpty()){
            etTask.setError("Task Required");
            etTask.requestFocus();
            return;
        }
        if(desc.isEmpty()){
            etDesc.setError("Description Required");
            etDesc.requestFocus();
            return;
        }
        if(finish.isEmpty()){
            etFinish.setError("Finish by Required");
            etFinish.requestFocus();
            return;
        }

        class SaveTask extends AsyncTask<Void,Void,Void>{


            @Override
            protected Void doInBackground(Void... voids) {
                Task saveTask=new Task();
                saveTask.setTask(task);
                saveTask.setDesc(desc);
                saveTask.setFinishBY(finish);
                saveTask.setFinished(false);

                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .taskDao().insert(saveTask);

                return null;

            }
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
                startActivity(new Intent(getApplicationContext(), MainTaskActivity.class));
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }

        SaveTask st=new SaveTask();
        st.execute();
    }
}
