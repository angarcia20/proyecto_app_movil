package com.example.poliviewer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.io.ByteArrayOutputStream;

public class crearevento extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    ActionBarDrawerToggle toogle;

    ImageView visualizacion;
    EditText titulo;
    EditText descripcion;
    EditText tipodeevento;
    EditText direccionevento;
    EditText fecha;
    EditText hora;


    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crearevento);

        drawerLayout = findViewById(R.id.drawer);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigationView);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toogle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawerOpen, R.string.drawerClose);
        drawerLayout.addDrawerListener(toogle);
        toogle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        visualizacion =  (ImageView) findViewById(R.id.visualizacion);




    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.profile:
                Toast.makeText(crearevento.this,"Registrar Usuario",Toast.LENGTH_SHORT).show();
                Intent login= new Intent(this, registro.class);
                startActivity(login);
                break;
            case R.id.contact:
                Toast.makeText(crearevento.this,"Escanear Qr",Toast.LENGTH_SHORT).show();
                Intent ConfirmarQr= new Intent(this, EscanearQr.class);
                startActivity(ConfirmarQr);
                break;
            case R.id.about:
                Toast.makeText(crearevento.this,"Inicio",Toast.LENGTH_SHORT).show();
                Intent bienvenida= new Intent(this, bienvenida.class);
                startActivity(bienvenida);
                break;
            case R.id.logout:
                Toast.makeText(crearevento.this,"Sesion Cerrada",Toast.LENGTH_SHORT).show();
                Intent logout= new Intent(this, MainActivity.class);
                startActivity(logout);
                break;
            case R.id.eventos:
                Toast.makeText(crearevento.this,"Eventos disponibles",Toast.LENGTH_SHORT).show();
                Intent eventos= new Intent(this, todosloseventos.class);
                startActivity(eventos);
                break;
            case R.id.crearevento:
                Toast.makeText(crearevento.this,"Crear evento",Toast.LENGTH_SHORT).show();
                Intent crearevento= new Intent(this, crearevento.class);
                startActivity(crearevento);
                break;
            case R.id.soporte:
                Toast.makeText(crearevento.this,"Crear soporte",Toast.LENGTH_SHORT).show();
                Intent soporte= new Intent(this, soporte.class);
                startActivity(soporte);
                break;

        }
        return false;
    }

    public void crear(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext());
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
         titulo = (EditText) findViewById(R.id.txt_titulo);
         descripcion= (EditText) findViewById(R.id.txt_descripcion);
        tipodeevento= (EditText)  findViewById(R.id.txt_tipoevento);
        direccionevento= (EditText) findViewById(R.id.txt_direccionevento);
         fecha= (EditText) findViewById(R.id.fecha);
         hora= (EditText) findViewById(R.id.hora);

         String tituloevent = titulo.getText().toString();
        String descripcionevent= descripcion.getText().toString();
        String tipodeevent= tipodeevento.getText().toString();
        String direccionevent= direccionevento.getText().toString();
        String fechaevent= fecha.getText().toString();
        String horaevent= hora.getText().toString();

        if(tituloevent.isEmpty() || descripcionevent.isEmpty() || tipodeevent.isEmpty() || direccionevent.isEmpty()
        || fechaevent.isEmpty() || horaevent.isEmpty()){
            Toast.makeText(getApplicationContext(), "Verifique que todos los campos esten llenos", Toast.LENGTH_SHORT).show();
        }else {

            ContentValues values = new ContentValues();

            values.put("titulo", tituloevent);
            values.put("descripcion", descripcionevent);
            values.put("tipodeevento", tipodeevent);
            values.put("direccionevento", direccionevent);
            values.put("fecha", fechaevent);
            values.put("hora", horaevent);
            BaseDeDatos.insert("crearevento", null, values);
            Toast.makeText(getApplicationContext(), "Evento Registrado", Toast.LENGTH_SHORT).show();
        }

    }
}
