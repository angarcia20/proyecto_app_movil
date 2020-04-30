package com.example.poliviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class cargaqr extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargaqr);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent= new Intent(cargaqr.this,bienvenida.class);

                Toast.makeText(getApplicationContext(), "Registro exitoso", Toast.LENGTH_SHORT).show();

                startActivity(intent);
            }
        },4000);
    }
}
