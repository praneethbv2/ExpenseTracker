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

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    EditText uname;
    EditText pwd;
    Button login;
    TextView regactivity;
    ProgressBar progressBar;
    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uname = findViewById(R.id.username);
        pwd = findViewById(R.id.pwd);
        login =findViewById(R.id.login);
        regactivity = findViewById(R.id.register);
        db = new DatabaseHelper(this);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = uname.getText().toString();
                String password = pwd.getText().toString();

                boolean verify = db.credentials(username,password);
                
                if(verify==true) Toast.makeText(MainActivity.this, "Successfully logged in", Toast.LENGTH_SHORT).show();
                else Toast.makeText(MainActivity.this, "Wrong username or password", Toast.LENGTH_SHORT).show();



            }
        });








        regactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regIntent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(regIntent);
            }
        });

    }
}
