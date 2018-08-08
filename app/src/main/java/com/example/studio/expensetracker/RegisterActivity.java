package com.example.studio.expensetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText firstname;
    EditText lastname;
    EditText username;
    EditText password;
    EditText confpassword;
    Button register;
    ProgressBar progressBar;
    TextView backlog;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);
        firstname = findViewById(R.id.firstname);
        lastname = findViewById(R.id.lastname);
        username = findViewById(R.id.username);
        password = findViewById(R.id.pwd);
        confpassword = findViewById(R.id.confpwd);
        register= findViewById(R.id.register);
        backlog = findViewById(R.id.backLogin);


        //Inserting data to database
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String sfname = firstname.getText().toString();
                String slname = lastname.getText().toString();
                String suname = username.getText().toString();
                String spwd = password.getText().toString();
                String sconfpwd = confpassword.getText().toString();


                //Checking all fields filled or not
                if(sfname.isEmpty()|| slname.isEmpty() || suname.isEmpty() || spwd.isEmpty() || sconfpwd.isEmpty())
                    Toast.makeText(RegisterActivity.this, "All fields need to be filled", Toast.LENGTH_SHORT).show();

                else
                {
                    if (spwd.equals(sconfpwd))
                    {
                        boolean checkuname = db.checkuname(suname);

                        if(checkuname)
                        {
                            boolean result = db.insertData(sfname,slname,suname,spwd);
                            if(result = true)
                            {
                                Toast.makeText(RegisterActivity.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                                backtoLogin();
                            }
                            else Toast.makeText(RegisterActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();

                        }
                        else Toast.makeText(RegisterActivity.this, "Username already exists", Toast.LENGTH_SHORT).show();

                    }
                    else Toast.makeText(RegisterActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();

                }





            }
        });



        //Back to loginpage
        backlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backtoLogin();
            }
        });
    }

    private void backtoLogin() {
        Intent backlog = new Intent(RegisterActivity.this,MainActivity.class);
        startActivity(backlog);

    }


}
