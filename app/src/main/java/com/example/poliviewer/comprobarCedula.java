package com.example.poliviewer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class comprobarCedula extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private EditText cedula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprobar_cedula);

        cedula = (EditText) findViewById(R.id.ncedula);

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext());
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        ContentValues values = new ContentValues();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }

    public void comprobar(View view) {

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext());

        String ncedula =cedula.getText().toString();

        if (!ncedula.isEmpty()) {
            boolean documento= admin.confirmacioncedula(ncedula);
            if (documento == true) {
                Toast.makeText(this, "Usuario se encuentra registrado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "No se encuentra registrado", Toast.LENGTH_SHORT).show();

            }
        } else {
            Toast.makeText(this, "El campo esta vacio", Toast.LENGTH_SHORT).show();

        }
    }
}
