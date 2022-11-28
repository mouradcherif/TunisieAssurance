package com.cherif.tunisieassurance;

import androidx.annotation.ContentView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddEditClient extends AppCompatActivity {

    private EditText nameEt,phoneEt,emailEt,regionEt;
    private String id,name,phone,email,region;
    private Boolean isEditMode;
    DBHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_client);
        db=new DBHelper(this);

        nameEt =  findViewById(R.id.nameEt);
        phoneEt =  findViewById(R.id.phoneEt);
        emailEt =  findViewById(R.id.emailEt);
        regionEt =  findViewById(R.id.locationEt);

        Intent intent = getIntent();
        isEditMode = intent.getBooleanExtra("isEditMode", false);

        if (isEditMode){
            id = intent.getStringExtra("id");
            name = intent.getStringExtra("name");
            phone = intent.getStringExtra("phone");
            email = intent.getStringExtra("email");
            region = intent.getStringExtra("region");

            nameEt.setText(name);
            phoneEt.setText(phone);
            emailEt.setText(email);
            regionEt.setText(region);

        }

    }


    @SuppressLint("NotConstructor")
    public void AddEditClient(View view) {
        String nameC = nameEt.getText().toString();
        String phoneC = phoneEt.getText().toString();
        String emailC = emailEt.getText().toString();
        String regionC = regionEt.getText().toString();

        if (nameEt.equals("")||emailEt.equals(""))
            Toast.makeText(AddEditClient.this, "Please enter all field information!",Toast.LENGTH_SHORT).show();
        else {
            if (isEditMode){
                db.updateClient(id,nameC,phoneC,emailC,regionC);
                Intent intent = new  Intent(getBaseContext(), ClientActivity.class);
                startActivity(intent);
            }else {
                Boolean insert = db.insertDataClient(nameC, phoneC, emailC, regionC);

                if (insert){
                    Toast.makeText(AddEditClient.this,"Client Registered successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new  Intent(getBaseContext(), ClientActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(AddEditClient.this,"Client Registration Failed", Toast.LENGTH_SHORT).show();
                }
            }

        }

    }

    public void DeleteClient(View view) {
        if (isEditMode){
            db.deleteClient(id);
            Intent intent = new  Intent(getBaseContext(), ClientActivity.class);
            startActivity(intent);
        }
    }
}