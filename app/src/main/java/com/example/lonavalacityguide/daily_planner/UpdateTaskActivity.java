    package com.example.lonavalacityguide.daily_planner;

    import android.content.DialogInterface;
    import android.content.Intent;
    import android.os.AsyncTask;
    import android.os.Bundle;
    import android.support.v7.app.AlertDialog;
    import android.support.v7.app.AppCompatActivity;
    import android.view.View;
    import android.widget.CheckBox;
    import android.widget.EditText;
    import android.widget.Toast;

    import com.example.lonavalacityguide.R;

    public class UpdateTaskActivity extends AppCompatActivity {

    private EditText etTask,etDesc,etFinish;
    private CheckBox checkBoxFinished;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_task);

        etTask=findViewById(R.id.editTextTask);
        etDesc=findViewById(R.id.editTextDesc);
        etFinish=findViewById(R.id.editTextFinishBy);

        checkBoxFinished=findViewById(R.id.checkBoxFinished);

        final Task task= (Task) getIntent().getSerializableExtra("task");
        loadTask(task);

        findViewById(R.id.button_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Clicked",Toast.LENGTH_SHORT).show();
                updateTask(task);
            }
        });

        findViewById(R.id.button_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(UpdateTaskActivity.this);
                builder.setTitle("Are you sure?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteTask(task);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        
                    }
                });

                AlertDialog alertDialog=builder.create();
                alertDialog.show();
            }
        });

    }

        private void deleteTask(final Task task) {
            class DeleteTask extends AsyncTask<Void,Void,Void>{


                @Override
                protected Void doInBackground(Void... voids) {
                    DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                            .taskDao()
                            .delete(task);
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_LONG).show();
                    finish();
                    startActivity(new Intent(UpdateTaskActivity.this, MainTaskActivity.class));
                }
            }
            DeleteTask deleteTask=new DeleteTask();
            deleteTask.execute();
        }

        private void updateTask(final Task task) {
        final String mTask=etTask.getText().toString().trim();
        final String mDesc=etDesc.getText().toString().trim();
        final String mFinish=etFinish.getText().toString().trim();

            if(mTask.isEmpty()){
                etTask.setError("Task Required");
                etTask.requestFocus();
                return;
            }
            if(mDesc.isEmpty()){
                etDesc.setError("Description Required");
                etDesc.requestFocus();
                return;
            }
            if(mFinish.isEmpty()){
                etFinish.setError("Finish by Required");
                etFinish.requestFocus();
                return;
            }

            class UpdateTask extends AsyncTask<Void,Void,Void>{

                @Override
                protected Void doInBackground(Void... voids) {
                    task.setTask(mTask);
                    task.setDesc(mDesc);
                    task.setFinishBY(mFinish);
                    task.setFinished(checkBoxFinished.isChecked());

                    DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                            .taskDao()
                            .update(task);
                    return null;

                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_LONG).show();
                    finish();
                    startActivity(new Intent(UpdateTaskActivity.this, MainTaskActivity.class));
                }
            }

            UpdateTask ut=new UpdateTask();
            ut.execute();
        }

        private void loadTask(Task task) {
        etTask.setText(task.getTask());
        etDesc.setText(task.getDesc());
        etFinish.setText(task.getFinishBY());
        checkBoxFinished.setChecked(task.isFinished());
        }
    }
