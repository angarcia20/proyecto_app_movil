package com.example.poliviewer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class registro extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Spinner spinner1;
    private Spinner eventos;
    private Spinner Horario;
    private Spinner Tipo_espectador;

    private EditText cedula, nombres,apellidos,telefono,correo;

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    ActionBarDrawerToggle toogle;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);



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




        String [] opciones = {"C.C","T.I","Cedula de extranjeria"};
        ArrayList<String> evento=consulta();
        //String [] horarios={"10:00 AM - 1:00 PM","2:00 PM - 5:00 PM","6:00 PM - 9:00 PM"};
        ArrayList<String> horarios = consultahora();
        String [] espectador={"Estudiante","Profesor","Invitado (Externo a la universidad)"};

        spinner1 = (Spinner) findViewById(R.id.spinnerid);
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opciones);
        spinner1.setAdapter(adapter);

        eventos=(Spinner) findViewById(R.id.spinnereventos);
        ArrayAdapter<CharSequence> adaptador= new ArrayAdapter(this,android.R.layout.simple_spinner_item,evento);
        eventos.setAdapter(adaptador);

        //ArrayList<String> aux=consultahora((String) eventos.getSelectedItem());

        Horario= (Spinner) findViewById(R.id.spinnerhorarios);
        ArrayAdapter<String> adapter2= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,horarios);
        Horario.setAdapter(adapter2);

        Tipo_espectador= (Spinner) findViewById(R.id.spinnerespectador);
        ArrayAdapter<String> adapter3= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,espectador);
        Tipo_espectador.setAdapter(adapter3);

    }



    public void registro(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext());
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        cedula = (EditText) findViewById(R.id.txt_cedula);
        nombres = (EditText) findViewById(R.id.txt_nombres);
        apellidos = (EditText) findViewById(R.id.txt_apellidos);
        telefono = (EditText) findViewById(R.id.txt_telefono);
        correo = (EditText) findViewById(R.id.txt_correo);
        String c_cedula = cedula.getText().toString();
        String c_nombres = nombres.getText().toString();
        String c_apellidos = apellidos.getText().toString();
        String c_telefono = telefono.getText().toString();
        String c_correo = correo.getText().toString();


        if (c_cedula.isEmpty() || c_nombres.isEmpty() || c_apellidos.isEmpty() ||
                c_telefono.isEmpty() || c_correo.isEmpty()) {

            Toast.makeText(getApplicationContext(), "Verifique que todos los campos esten llenos", Toast.LENGTH_SHORT).show();
        } else {


            ContentValues values = new ContentValues();

            values.put("cedula", c_cedula);
            values.put("nombres", c_nombres);
            values.put("apellidos", c_apellidos);
            values.put("telefono", c_telefono);
            values.put("correo", c_correo);
            values.put("idevento", 1);
            values.put("idhorario", 1);
            values.put("espectador", "Estudiante");
            BaseDeDatos.insert("registros", null, values);
            Toast.makeText(getApplicationContext(), "Usuario Registrado", Toast.LENGTH_SHORT).show();


            Intent login = new Intent(this, GeneradorQr.class);
            login.putExtra("cedula", c_cedula);
            login.putExtra("nombres", c_nombres);
            login.putExtra("apellidos", c_apellidos);
            login.putExtra("telefono", c_telefono);
            login.putExtra("correo", c_correo);
            startActivity(login);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.profile:
                Toast.makeText(registro.this,"Registrar Usuario",Toast.LENGTH_SHORT).show();
                Intent login= new Intent(this, registro.class);
                startActivity(login);
                break;
            case R.id.contact:
                Toast.makeText(registro.this,"Escanear Qr",Toast.LENGTH_SHORT).show();
                Intent ConfirmarQr= new Intent(this, EscanearQr.class);
                startActivity(ConfirmarQr);
                break;
            case R.id.about:
                Toast.makeText(registro.this,"Inicio",Toast.LENGTH_SHORT).show();
                Intent bienvenida= new Intent(this, bienvenida.class);
                startActivity(bienvenida);
                break;
            case R.id.logout:
                Toast.makeText(registro.this,"Sesion Cerrada",Toast.LENGTH_SHORT).show();
                Intent logout= new Intent(this, MainActivity.class);
                startActivity(logout);
                break;
            case R.id.eventos:
                Toast.makeText(registro.this,"Eventos disponibles",Toast.LENGTH_SHORT).show();
                Intent eventos= new Intent(this, todosloseventos.class);
                startActivity(eventos);
                break;
            case R.id.crearevento:
                Toast.makeText(registro.this,"Crear evento",Toast.LENGTH_SHORT).show();
                Intent crearevento= new Intent(this, crearevento.class);
                startActivity(crearevento);
                break;
            case R.id.soporte:
                Toast.makeText(registro.this,"Crear soporte",Toast.LENGTH_SHORT).show();
                Intent soporte= new Intent(this, soporte.class);
                startActivity(soporte);
                break;


        }
        return false;
    }
    private ArrayList<String> consulta() {

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext());
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        ArrayList<String> neventos = new ArrayList<String>();
        Cursor cursor = BaseDeDatos.rawQuery("SELECT * FROM crearevento", null);

        while (cursor.moveToNext()) {
            neventos.add(cursor.getInt(0)+" - "+cursor.getString(1));
        }
        return neventos;
    }

    private ArrayList<String> consultahora(){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext());
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        ArrayList<String> heventos = new ArrayList<String>();
        Cursor cursor = BaseDeDatos.rawQuery("SELECT * FROM crearevento", null);

        while (cursor.moveToNext()) {
            heventos.add(cursor.getString(6));
        }
        return heventos;
    }


    }

