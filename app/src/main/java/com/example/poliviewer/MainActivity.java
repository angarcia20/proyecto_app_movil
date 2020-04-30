package com.example.poliviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText c_usuario, c_contraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        c_usuario = (EditText) findViewById(R.id.txt_usuario);
        c_contraseña = (EditText) findViewById(R.id.txt_contraseña);
    }
    public void logueo(View view) {

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext());
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String usuario = c_usuario.getText().toString();
        String contraseña = c_contraseña.getText().toString();

        if (!usuario.isEmpty() && !contraseña.isEmpty()) {
            boolean user= admin.usuario(usuario);
            boolean pass =admin.contraseña(contraseña);
            if (user== true && pass== true) {
                    BaseDeDatos.close();
                Intent intent= new Intent(MainActivity.this, cargaLogin.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Usuario o contraseña incorrecta", Toast.LENGTH_SHORT).show();
                BaseDeDatos.close();
            }
        } else {
            Toast.makeText(this, "Hay un campo vacio", Toast.LENGTH_SHORT).show();
            BaseDeDatos.close();
        }
    }
}
