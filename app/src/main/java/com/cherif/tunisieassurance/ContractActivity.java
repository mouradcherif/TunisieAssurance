package com.cherif.tunisieassurance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ContractActivity extends AppCompatActivity {

    private FloatingActionButton fab;
    private RecyclerView contractRV;
    private DBHelper dbHelper;
    private AdapterContract adapterContract;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contract);

        dbHelper = new DBHelper(this);

        fab = findViewById(R.id.addclt);
        contractRV = findViewById(R.id.lvcontract);
        contractRV.setHasFixedSize(true);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContractActivity.this,AddEditContract.class);
                startActivity(intent);
            }
        });

        loadData();
    }

    private void loadData() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        contractRV.setLayoutManager(manager);
        adapterContract = new AdapterContract(this,dbHelper.getAllDataContract());
        contractRV.setAdapter(adapterContract);
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadData();
    }
}