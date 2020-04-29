package com.example.poliviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class splash_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent= new Intent(splash_screen.this,MainActivity.class);
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext());

                String usuario = "angarcia20";
                String contraseña = "prueba1";

                admin.agregar(usuario,contraseña);
                Toast.makeText(getApplicationContext(),"Usuario cargado", Toast.LENGTH_SHORT).show();




                startActivity(intent);
            }
        },4000);
    }
}
