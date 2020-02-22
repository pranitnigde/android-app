package com.example.lonavalacityguide.login_registration;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lonavalacityguide.MainActivity;
import com.example.lonavalacityguide.R;

public class LoginActivity extends AppCompatActivity {
    TextInputEditText username,password;
    TextView txtRegister;
    private DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        databaseHelper = new DatabaseHelper(getApplicationContext());

        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        txtRegister=findViewById(R.id.txtRegister);

        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,Registration.class));
            }
        });
    }

    public void login(View view) {
        String user=username.getText().toString().trim();
        String pass=password.getText().toString().trim();
        if (user.equals("")) {
            username.setError("please enter email address");
            username.requestFocus();
        }
        else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(user).matches()) {
            username.setError("please enter valid email address");
            username.requestFocus();
        }
        if (pass.equals("")) {
            password.setError("please enter password");
            password.requestFocus();
        }
        else if (pass.length() < 6) {
            password.setError("password minimum contain 6 character");
            password.requestFocus();
        }
        if (!user.equals("") &&
                pass.length() >= 6 &&
                !pass.equals("")
                && android.util.Patterns.EMAIL_ADDRESS.matcher(user).matches()) {
            // do  your action
            if(databaseHelper.checkUser(user,pass)){
                clearFields();


                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
            else{
                Toast.makeText(getApplicationContext(),"Incorrect credentials! Please enter correct data",Toast.LENGTH_SHORT).show();
            }


        }

    }

    private void clearFields() {
        username.setText(null);
        password.setText(null);
    }
}
