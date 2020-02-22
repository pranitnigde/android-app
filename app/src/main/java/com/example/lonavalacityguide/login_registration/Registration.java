package com.example.lonavalacityguide.login_registration;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lonavalacityguide.R;

public class Registration extends AppCompatActivity {
    EditText etName,etContact,etEmail,etPassword,etConfirmPassword;
    Button btnRegister;
    private DatabaseHelper db;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        db=new DatabaseHelper(this.getApplicationContext());
        user=new User();

        etName=findViewById(R.id.etName);
        etContact=findViewById(R.id.etContact);
        etEmail=findViewById(R.id.etEmail);
        etPassword=findViewById(R.id.etPassword);
        etConfirmPassword=findViewById(R.id.etConfirmPassword);
        btnRegister=findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=etName.getText().toString();
                String contact=etContact.getText().toString();
                String email=etEmail.getText().toString();
                String password=etPassword.getText().toString();
                String confirmPassword=etConfirmPassword.getText().toString();

                if (etEmail.getText().toString().equals("")) {
                    etEmail.setError("Please enter email address");
                    etEmail.requestFocus();
                }
                else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(etEmail.getText().toString()).matches()) {
                    etEmail.setError("Please enter valid email address");
                    etEmail.requestFocus();
                }
                if (etPassword.getText().toString().equals("")) {
                    etPassword.setError("Please enter password");
                    etPassword.requestFocus();
                }
                else if (etPassword.getText().toString().trim().length() < 6) {
                    etPassword.setError("Password should have minimum 6 character");
                    etPassword.requestFocus();
                }
                if (etConfirmPassword.getText().toString().equals("")) {
                    etConfirmPassword.setError("Please enter password");
                    etConfirmPassword.requestFocus();
                }
                else if (etConfirmPassword.getText().toString().trim().length() < 6) {
                    etConfirmPassword.setError("Password should have minimum 6 character");
                    etConfirmPassword.requestFocus();
                }
                else if(etConfirmPassword.getText().toString()!=etPassword.getText().toString())
                {
                    etConfirmPassword.setError("Password should match!");
                    etConfirmPassword.requestFocus();
                }
                if (!etEmail.getText().toString().equals("") &&
                        etPassword.getText().toString().length() >= 6 &&
                        !etPassword.getText().toString().equals("")
                        && etConfirmPassword.getText().toString().length() >= 6 &&
                        !etConfirmPassword.equals("")
                       // && etPassword.getText().toString() == etConfirmPassword.getText().toString()
                        && android.util.Patterns.EMAIL_ADDRESS.matcher(etEmail.getText().toString()).matches()) {
                    // do  your action
                    if(!name.equals("")&&!contact.equals("")&&!email.equals("")&&!password.equals("")&&!confirmPassword.equals("")){

                        if(!db.checkUser(email.trim())){
                            user.setName(name.trim());
                            user.setEmail(email.trim());
                            user.setPassword(password.trim());
                            user.setContact(contact);

                            db.addUser(user);
                            Toast.makeText(getApplicationContext(),"Registration Successful!",Toast.LENGTH_SHORT).show();
                            clearAllFields();
                            startActivity(new Intent(Registration.this,LoginActivity.class));
                        }else{
                            Toast.makeText(getApplicationContext(),"User is already registered!",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Fields caannot be empty", Toast.LENGTH_SHORT).show();

                    }


                }





            }
        });

    }

    private void clearAllFields() {
        etName.setText(null);
        etContact.setText(null);
        etEmail.setText(null);
        etPassword.setText(null);
        etConfirmPassword.setText(null);
    }

}
