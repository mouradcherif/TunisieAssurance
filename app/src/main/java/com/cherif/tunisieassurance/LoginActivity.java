package com.cherif.tunisieassurance;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    EditText email, password;
    DBHelper db;
    TextView login, register;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.username_input);
        password = (EditText) findViewById(R.id.password_input);
        db = new DBHelper(this);

        login = (TextView) findViewById(R.id.btnlogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = email.getText().toString();
                String pass = password.getText().toString();
                if (email.equals("") || pass.equals(""))
                    Toast.makeText(LoginActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkemailpass = db.checkemailpassword(mail, pass);
                    if (checkemailpass == true) {
                        Toast.makeText(LoginActivity.this, "Login successfull", Toast.LENGTH_SHORT).show();
                        Intent intent2 = new Intent(LoginActivity.this, MenuActivity.class);
                        startActivity(intent2);
                    } else {
                        Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });
        register = (TextView) findViewById(R.id.btnregister);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                Intent intent1 = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent1);

            }

        });
    }
}


    /*public void Login(View view) {
        String mail = email.getText().toString();
        String pass = password.getText().toString();
        if(email.equals("")||pass.equals(""))
            Toast.makeText(LoginActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
        else{
            Boolean checkemailpass = db.checkemailpassword(mail, pass);
            if(checkemailpass==true){
                Toast.makeText(LoginActivity.this, "Login successfull", Toast.LENGTH_SHORT).show();
                Intent intent2  = new Intent(LoginActivity.this, MenuActivity.class);
                startActivity(intent2);
            }else{
                Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void Register(View view) {
        Toast.makeText(LoginActivity.this, "Before intent", Toast.LENGTH_SHORT).show();
        Intent intent1 = new  Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent1);
        Toast.makeText(LoginActivity.this, "After intent", Toast.LENGTH_SHORT).show();
    }*/



