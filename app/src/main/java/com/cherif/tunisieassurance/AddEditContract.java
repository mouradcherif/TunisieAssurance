package com.cherif.tunisieassurance;

import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class AddEditContract extends AppCompatActivity {

    private EditText refEt,datedebutEt,datefinEt,redevenceEt;
    private RelativeLayout laydelete;
    private String id , ref ,datedebut, datefin, redevence;
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
        laydelete = (RelativeLayout) findViewById(R.id.laydelete);


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
        }else{
            laydelete.setVisibility(View.INVISIBLE);
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