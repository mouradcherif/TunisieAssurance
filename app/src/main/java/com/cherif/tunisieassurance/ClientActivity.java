package com.cherif.tunisieassurance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ClientActivity extends AppCompatActivity {

    private FloatingActionButton fab,fab1;
    private RecyclerView contactRV;
    private DBHelper dbHelper;
    private AdapterContact adapterContact;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        dbHelper = new DBHelper(this);



        fab = findViewById(R.id.addclt);
        fab1 = findViewById(R.id.addclt2);
        contactRV = findViewById(R.id.lvcontact);
        contactRV.setHasFixedSize(true);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClientActivity.this,AddEditClient.class);
                startActivity(intent);
            }
        });

        searchView = findViewById(R.id.search_bar);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchContact(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchContact(newText);
                return true;
            }


        });

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClientActivity.this,MenuActivity.class);
                startActivity(intent);
            }
        });

        loadData();
    }

    private void searchContact(String query) {
        adapterContact = new AdapterContact(this,dbHelper.getSearchContact(query));
        contactRV.setAdapter(adapterContact);
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