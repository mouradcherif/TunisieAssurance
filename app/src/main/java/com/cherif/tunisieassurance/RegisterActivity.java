package com.cherif.tunisieassurance;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    EditText email , password , repassword;
    DBHelper db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = (EditText) findViewById(R.id.username_input);
        password = (EditText) findViewById(R.id.password_input);
        repassword = (EditText) findViewById(R.id.repassword_input);
        db = new DBHelper(this);

    }
    public void Register(View view) {
        String mail = email.getText().toString();
        String pass = password.getText().toString();
        String repass = repassword.getText().toString();

        if (mail.equals("")||pass.equals("")||repass.equals(""))
            Toast.makeText(RegisterActivity.this, "Please enter all field information!",Toast.LENGTH_SHORT).show();
        else {
            if(pass.equals(repass)) {
               Boolean checkmail = db.checkemail(mail);
               if (checkmail==false){
                   Boolean insert = db.insertData(mail, pass);
                   if (insert==true){
                       Toast.makeText(RegisterActivity.this,"Registered successfully", Toast.LENGTH_SHORT).show();
                       Intent intent = new  Intent(getBaseContext(), LoginActivity.class);
                       startActivity(intent);
                   }
                   else {
                       Toast.makeText(RegisterActivity.this,"Registration Failed", Toast.LENGTH_SHORT).show();
                   }
               }
               else {
                   Toast.makeText(RegisterActivity.this,"User already exists! Please Login", Toast.LENGTH_SHORT).show();
               }
            }
            else {
                Toast.makeText(RegisterActivity.this,"Passwords not matching", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void GoLogin(View view) {
        Intent intent = new  Intent(getBaseContext(), LoginActivity.class);
        startActivity(intent);
    }
}
