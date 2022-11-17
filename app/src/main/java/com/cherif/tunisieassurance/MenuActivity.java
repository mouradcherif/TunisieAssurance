package com.cherif.tunisieassurance;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void GoContracts(View view) {
        Intent intent = new  Intent(getBaseContext(), ContractActivity.class);
        startActivity(intent);
    }

    public void GoClients(View view) {
        Intent intent = new  Intent(getBaseContext(), ClientActivity.class);
        startActivity(intent);
    }

    public void GoGithub(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/mouradcherif"));
        startActivity(browserIntent);
    }

    public void GoSignIn(View view) {
        Intent intent = new  Intent(getBaseContext(), LoginActivity.class);
        startActivity(intent);
    }
}