package com.cherif.tunisieassurance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ContractActivity extends AppCompatActivity {

    private FloatingActionButton fab,fab1;
    private RecyclerView contractRV;
    private DBHelper dbHelper;
    private AdapterContract adapterContract;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contract);

        dbHelper = new DBHelper(this);

        fab = findViewById(R.id.addclt);
        fab1 = findViewById(R.id.addclt2);
        contractRV = findViewById(R.id.lvcontract);
        contractRV.setHasFixedSize(true);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContractActivity.this,AddEditContract.class);
                startActivity(intent);
            }
        });

        searchView = findViewById(R.id.search_bar);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchContract(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchContract(newText);
                return true;
            }


        });

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContractActivity.this,MenuActivity.class);
                startActivity(intent);
            }
        });

        loadData();
    }
    private void searchContract(String query) {
        adapterContract = new AdapterContract(this,dbHelper.getSearchContract(query));
        contractRV.setAdapter(adapterContract);
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