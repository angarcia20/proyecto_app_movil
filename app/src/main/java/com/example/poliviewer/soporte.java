package com.example.poliviewer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class soporte extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    EditText tema;
    EditText descripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soporte);
    }


    public void enviarsoporte(View view) {

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext());
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        tema = (EditText) findViewById(R.id.txt_tema);
        descripcion = (EditText) findViewById(R.id.descripcionsoporte);

        if(tema.getText().toString().isEmpty() || descripcion.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Debe llenar los campos", Toast.LENGTH_SHORT).show();

        }else {

            ContentValues values = new ContentValues();

            values.put("tema", tema.getText().toString());
            values.put("descripcion", descripcion.getText().toString());
            BaseDeDatos.insert("soporte", null, values);
            Toast.makeText(getApplicationContext(), "Soporte creado", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, cargaqr.class);
            startActivity(intent);
        }







    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.profile:
                Toast.makeText(soporte.this,"Registrar Usuario",Toast.LENGTH_SHORT).show();
                Intent login= new Intent(this, registro.class);
                startActivity(login);
                break;
            case R.id.contact:
                Toast.makeText(soporte.this,"Escanear Qr",Toast.LENGTH_SHORT).show();
                Intent ConfirmarQr= new Intent(this, EscanearQr.class);
                startActivity(ConfirmarQr);
                break;
            case R.id.about:
                Toast.makeText(soporte.this,"Inicio",Toast.LENGTH_SHORT).show();
                Intent bienvenida= new Intent(this, bienvenida.class);
                startActivity(bienvenida);
                break;
            case R.id.logout:
                Toast.makeText(soporte.this,"Sesion Cerrada",Toast.LENGTH_SHORT).show();
                Intent logout= new Intent(this, MainActivity.class);
                startActivity(logout);
                break;
            case R.id.eventos:
                Toast.makeText(soporte.this,"Eventos disponibles",Toast.LENGTH_SHORT).show();
                Intent eventos= new Intent(this, todosloseventos.class);
                startActivity(eventos);
                break;
            case R.id.crearevento:
                Toast.makeText(soporte.this,"Crear evento",Toast.LENGTH_SHORT).show();
                Intent crearevento= new Intent(this, crearevento.class);
                startActivity(crearevento);
                break;
            case R.id.soporte:
                Toast.makeText(soporte.this,"Crear soporte",Toast.LENGTH_SHORT).show();
                Intent soporte= new Intent(this, soporte.class);
                startActivity(soporte);
                break;


        }
        return false;
    }
}
