package com.yunhasnawa.kontaksederhana;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnTambahKontak_onClick(View view)
    {
        // Intent
        Intent i = new Intent(MainActivity.this, TambahKontakActivity.class);
        this.startActivity(i);
    }

    public void btnLihatKontak_onClick(View view)
    {
        // Intent
        Intent i = new Intent(MainActivity.this, LihatKontakActivity.class);
        this.startActivity(i);
    }
}