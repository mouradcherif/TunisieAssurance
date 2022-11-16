package com.cherif.tunisieassurance;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    EditText email , password ;
    DBHelper db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.username_input);
        password = (EditText) findViewById(R.id.password_input);
        db = new DBHelper(this);

    }
    public void Login(View view) {
        String mail = email.getText().toString();
        String pass = password.getText().toString();
        if(email.equals("")||pass.equals(""))
            Toast.makeText(LoginActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
        else{
            Boolean checkemailpass = db.checkemailpassword(mail, pass);
            if(checkemailpass==true){
                Toast.makeText(LoginActivity.this, "Login successfull", Toast.LENGTH_SHORT).show();
                Intent intent  = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void Register(View view) {

        Intent intent1 = new  Intent(getBaseContext(), RegisterActivity.class);
        startActivity(intent1);
    }


}
