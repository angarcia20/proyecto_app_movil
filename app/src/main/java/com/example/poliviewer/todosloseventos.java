package com.example.poliviewer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class todosloseventos extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    ListView lista;
/*
    String[][] datos= {
            {"Big Data","5","Es un término evolutivo que describe cualquier cantidad voluminosa de datos estructurados, semiestructurados y no estructurados que tienen el potencial de ser extraídos para obtener información.","Cra 112 # 13a-24"},
            {"Seguridad informatica","8","La seguridad informática es una disciplina que se encarga de proteger la integridad y la privacidad de la información almacenada en un sistema informático. De todas formas, no existe ninguna técnica que permita asegurar la inviolabilidad de un sistema.","Cll 26 #33-24"},
            {"Lo que el mundo no sabe","4","Una pandemia de influenza es un brote mundial de un nuevo virus de influenza A que es muy diferente de los virus de la influenza estacional A que han circulado entre las personas recientemente o que están en circulación en el momento.","Cra 7 #53-2"},

    };*/


    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    ActionBarDrawerToggle toogle;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todosloseventos);

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

        String[][] datos = datos();
        if(datos == null){
            Toast.makeText(getApplicationContext(), "No hay eventos disponibles", Toast.LENGTH_SHORT).show();

        }else {
            int[] datosImg= new int[datos.length];
            for (int i = 0 ; i<datosImg.length; i++){
                datosImg[i] = R.drawable.logo_poli;

            }

            lista = (ListView) findViewById(R.id.lvLista);

            lista.setAdapter(new Adaptador(this, datos, datosImg));
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.profile:
                Toast.makeText(todosloseventos.this,"Registrar Usuario",Toast.LENGTH_SHORT).show();
                Intent login= new Intent(this, registro.class);
                startActivity(login);
                break;
            case R.id.contact:
                Toast.makeText(todosloseventos.this,"Escanear Qr",Toast.LENGTH_SHORT).show();
                Intent ConfirmarQr= new Intent(this, EscanearQr.class);
                startActivity(ConfirmarQr);
                break;
            case R.id.about:
                Toast.makeText(todosloseventos.this,"Inicio",Toast.LENGTH_SHORT).show();
                Intent inicio= new Intent(this, bienvenida.class);
                startActivity(inicio);
                break;
            case R.id.logout:
                Toast.makeText(todosloseventos.this,"Sesion Cerrada",Toast.LENGTH_SHORT).show();
                Intent logout= new Intent(this, MainActivity.class);
                startActivity(logout);
                break;
            case R.id.eventos:
                Toast.makeText(todosloseventos.this,"Eventos disponibles",Toast.LENGTH_SHORT).show();
                Intent eventos= new Intent(this, todosloseventos.class);
                startActivity(eventos);
                break;
            case R.id.crearevento:
                Toast.makeText(todosloseventos.this,"Crear evento",Toast.LENGTH_SHORT).show();
                Intent crearevento= new Intent(this, crearevento.class);
                startActivity(crearevento);
                break;
            case R.id.soporte:
                Toast.makeText(todosloseventos.this,"Crear soporte",Toast.LENGTH_SHORT).show();
                Intent soporte= new Intent(this, soporte.class);
                startActivity(soporte);
                break;

        }
        return false;
    }

    public String[][] datos(){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext());

        String[][] datos = new  AdminSQLiteOpenHelper(this).consultareventos();

        return datos;
    }
}
