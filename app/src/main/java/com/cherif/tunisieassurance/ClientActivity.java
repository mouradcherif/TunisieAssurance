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

public class ClientActivity extends AppCompatActivity {

    private FloatingActionButton fab;
    private RecyclerView contactRV;

    private DBHelper dbHelper;

    private AdapterContact adapterContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        dbHelper = new DBHelper(this);

        fab = findViewById(R.id.addclt);
        contactRV = findViewById(R.id.lvcontact);

        contactRV.setHasFixedSize(true);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClientActivity.this,AddEditClient.class);
                startActivity(intent);
            }
        });

        loadData();
    }

    private void loadData() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        contactRV.setLayoutManager(manager);
        adapterContact = new AdapterContact(this,dbHelper.getAllData());
        contactRV.setAdapter(adapterContact);
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadData();
    }
}