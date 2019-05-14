package com.spicystar.jsoosu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginPage extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private Button Login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);


        Name = findViewById(R.id.etName);
        Password = findViewById(R.id.etPass);
        Login = findViewById(R.id.btnLogin);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = Name.getText().toString().trim();
                String pass = Password.getText().toString().trim();

                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pass)) {
                    Toast.makeText(getApplicationContext(), "At least one box is empty", Toast.LENGTH_SHORT).show();
                } else {
                    validate(name, pass);
                }
            }
        });

    }

        private void validate(String userName, String userPassword) {
            if ((userName.equals("JSO")) && (userPassword.equals("634556"))) {
                //shows that the login is successful in a message then moves to a newActivity
                Toast.makeText(getApplicationContext(),"Login Successful", Toast.LENGTH_SHORT).show();
                moveToNewActivity();
            }
            else {
                //error message saying that the login was unsuccessful
                Toast.makeText(getApplicationContext(),"Login Unsuccessful", Toast.LENGTH_SHORT).show();
            }

        }

        private void moveToNewActivity(){

            Intent loginToAdmin = new Intent(LoginPage.this, AdminActivity.class);
            LoginPage.this.startActivity(loginToAdmin);


        }


}
