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

public class AddEditContract extends AppCompatActivity {

    private EditText refEt,datedebutEt,datefinEt,redevenceEt,nameEt,regionEt;
    String id , ref ,datedebut, datefin, redevence;
    DBHelper db;
    private boolean isEditMode;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_contract);
        db=new DBHelper(this);


        refEt = (EditText) findViewById(R.id.refEt);
        datedebutEt = (EditText) findViewById(R.id.datedebutEt);
        datefinEt = (EditText) findViewById(R.id.datefinEt);
        redevenceEt = (EditText) findViewById(R.id.redevenceEt);


        Intent intent = getIntent();
        isEditMode = intent.getBooleanExtra("isEditMode", false);

        if (isEditMode){
            id = intent.getStringExtra("id");
            ref = intent.getStringExtra("ref");
            datedebut = intent.getStringExtra("datedebut");
            datefin = intent.getStringExtra("datefin");
            redevence = intent.getStringExtra("redevence");

            refEt.setText(ref);
            datedebutEt.setText(datedebut);
            datefinEt.setText(datefin);
            redevenceEt.setText(redevence);
        }

    }


    @SuppressLint("NotConstructor")
    public void AddEditContract(View view) {
        String refc = refEt.getText().toString();
        String datedebutc = datedebutEt.getText().toString();
        String datefinc = datefinEt.getText().toString();
        String redevencec = redevenceEt.getText().toString();

        if (refEt.equals("")||datedebutEt.equals("")||datefinEt.equals("")||redevenceEt.equals(""))
            Toast.makeText(AddEditContract.this, "Please enter all field information!",Toast.LENGTH_SHORT).show();
        else {
            if (isEditMode){
                db.updateContract(id,refc,datedebutc,datefinc,redevencec);
                Intent intent = new  Intent(getBaseContext(), ContractActivity.class);
                startActivity(intent);
            }else{
                Boolean insert = db.insertDataContract(refc, datedebutc, datefinc, redevencec);
                if (insert){
                    Toast.makeText(AddEditContract.this,"Contract Registered successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new  Intent(getBaseContext(), ContractActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(AddEditContract.this,"Contract Registration Failed", Toast.LENGTH_SHORT).show();
                }
            }

        }

    }

    public void DeleteContract(View view) {
        if (isEditMode){
            db.deleteContract(id);
            Intent intent = new  Intent(getBaseContext(), ContractActivity.class);
            startActivity(intent);
        }
    }
}